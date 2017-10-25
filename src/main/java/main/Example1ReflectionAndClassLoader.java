package main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Example1ReflectionAndClassLoader {

    public static void main() throws Exception {
        System.out.println("Lets do: new SomeClassToLoad().print()");
        new SomeClassToLoad().print();

        System.out.println("Lets do the same with reflection");
        Class someClassToLoadClass1 = SomeClassToLoad.class;
        SomeClassToLoad instance1 = (SomeClassToLoad) someClassToLoadClass1.newInstance();
        instance1.print();

        System.out.println("Lets do the same using the classLoader");
        ClassLoader someClassToLoadClassLoader = SomeClassToLoad.class.getClassLoader();
        Class someClassToLoadClass2 = someClassToLoadClassLoader.loadClass("main.SomeClassToLoad");
        SomeClassToLoad instance2 = (SomeClassToLoad) someClassToLoadClass2.newInstance();
        instance2.print();

        System.out.println("The classes are all the same: someClassToLoadClass1 == someClassToLoadClass2 == SomeClassToLoad.class "
                + (someClassToLoadClass1 == someClassToLoadClass2)
                + ", "
                + (someClassToLoadClass1 == SomeClassToLoad.class)
                + ", "
                + (someClassToLoadClass2 == SomeClassToLoad.class)
                + "..."
        );
        System.out.println("A few comments: ");
        System.out.println("- Even if something si private you can do setAccessible(true) and access it");
        
        System.out.println("- The reflection api has java.lang.reflect.Proxy, use it if you can to avoid byte code manipulation!!!!");
        SomeInterface someInterface = (SomeInterface) Proxy.newProxyInstance(SomeInterface.class.getClassLoader(),
                new Class[]{SomeInterface.class},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("print")) {
                    System.out.println("PUM PROXY");
                    return null;
                }
                return method.invoke(proxy, args);
            }

        });
        someInterface.print();

    }

}
