package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Example3JClassLib {

    public static void main() throws Exception {
        System.out.println("Reading class bytes");
        ClassLoader mainClassLoader = SomeClassToLoad.class.getClassLoader();
        String classPathInsideTheJar = "main/SomeClassToLoad.class";
        InputStream is = mainClassLoader.getResourceAsStream(classPathInsideTheJar);
        byte[] buf = new byte[is.available()];
        int len = is.read(buf);
        System.out.println("Now I must parse the byte[] and then change some of them (before calling the classloader)");
        System.out.println("But first lets check the class with https://github.com/ingokegel/jclasslib !!!");
        File f = File.createTempFile("some", ".class");
        try (FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile())) {
            fos.write(buf, 0, len);
        }
        System.out.println("Saved as " + f.getAbsoluteFile());
        org.gjt.jclasslib.browser.BrowserApplication.main(new String[]{f.getAbsolutePath()});
        f.deleteOnExit();
    }
}
