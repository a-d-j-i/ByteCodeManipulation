package main;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import static main.Example4ByteCodeModification.runTheNewClassBuffer;

public class Example6Javassist {

    public static void main() throws Exception {
        ClassLoader mainClassLoader = SomeClassToLoad.class.getClassLoader();

        System.out.println("Here is how it is done with javassist");
        ClassPool pool = ClassPool.getDefault();
        System.out.println("javassist CtClass is a parsed class");
        CtClass cc = pool.get("main.SomeClassToLoad");
        CtMethod printMethod = cc.getMethod("print", "()V");
        System.out.println("javassist has an internal compiller so I can do setBody on a CtMethod");
        printMethod.setBody("{ System.out.println(\"PUM WITH JAVASSIST\"); }");

        System.out.println("finally I can save it to a file, get a class or a byte[]");
        byte[] newBuf = cc.toBytecode();
        cc.detach();

        runTheNewClassBuffer(newBuf);
    }
}
