package main;

import java.io.InputStream;
import static main.Main.bytesToHex;

public class Example2ClassCastException {

    public static void main() throws Exception {
        System.out.println("Lets load the same class with our own classLoaders");
        ClassLoader mainClassLoader = SomeClassToLoad.class.getClassLoader();
        Class someClassToLoadClass = mainClassLoader.loadClass("main.SomeClassToLoad");

        System.out.println("I must read the class as data (byte[]), for that I use main classLoader so I get the same bytes");
        byte[] buf = readTheClassBytes();

        ClassLoader classLoader1 = new ClassLoader() {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                if (name.equals("main.SomeClassToLoad")) {
                    Class ret = defineClass("main.SomeClassToLoad", buf, 0, buf.length);
                    System.out.println("Returning the same class but related to this classLoader" + ret.getClassLoader());
                    return ret;
                }
                return super.loadClass(name, resolve);
            }
        };
        Class someClassToLoadClass1 = classLoader1.loadClass("main.SomeClassToLoad");

        ClassLoader classLoader2 = new ClassLoader() {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                if (name.equals("main.SomeClassToLoad")) {
                    Class ret = defineClass("main.SomeClassToLoad", buf, 0, buf.length);
                    System.out.println("Some another class loader, just in case that you don't believe " + ret.getClassLoader());
                    return ret;
                }
                return super.loadClass(name, resolve);
            }
        };
        Class someClassToLoadClass2 = classLoader2.loadClass("main.SomeClassToLoad");

        System.out.println("SomeClassToLoad.class, someClassToLoadClass1 and someClassToLoadClass2 have different classLoaders: "
                + mainClassLoader + " "
                + someClassToLoadClass1.getClassLoader()
                + " "
                + someClassToLoadClass2.getClassLoader()
        );

        System.out.println("So the classes are not the same someClassToLoadClass != someClassToLoadClass1 != someClassToLoadClass2 "
                + (someClassToLoadClass == someClassToLoadClass1)
                + " "
                + (someClassToLoadClass == someClassToLoadClass2)
                + " "
                + (someClassToLoadClass1 == someClassToLoadClass2)
                + "..."
        );

        System.out.println("We can make an instance with mainClassLoader");
        SomeClassToLoad mainInstance = (SomeClassToLoad) someClassToLoadClass.newInstance();
        mainInstance.print();

        try {
            System.out.println("But if we use the otherClassLoader we can not cast it");
            SomeClassToLoad otherInstance = (SomeClassToLoad) someClassToLoadClass1.newInstance();
            System.out.println("WON'T HAPPEN");
            otherInstance.print();
        } catch (java.lang.ClassCastException e) {
            System.out.println("GOT A STRANGE EXCEPTION " + e + " PLEASE ADD THE CLASSLOADER TO THE MESSAGE!!!");
        }

        System.out.println("The solution, we won't cast, lets use reflection");
        Object otherInstance = someClassToLoadClass1.newInstance();
        someClassToLoadClass1.getDeclaredMethod("print").invoke(otherInstance);
    }

    public static byte[] readTheClassBytes() throws Exception {
        String classPathInsideTheJar = "main/SomeClassToLoad.class";
        byte[] buf;
        int len;
        try (InputStream is = SomeClassToLoad.class.getClassLoader().getResourceAsStream(classPathInsideTheJar)) {
            buf = new byte[is.available()];
            len = is.read(buf);
        }
        System.out.println("Got the same bytes that mainClassLoader uses: " + len + " " + bytesToHex(buf));
        return buf;
    }
}
