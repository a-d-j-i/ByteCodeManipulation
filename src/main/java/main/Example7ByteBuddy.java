package main;

import static main.Example4ByteCodeModification.runTheNewClassBuffer;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class Example7ByteBuddy {

    public static void main() throws Exception {
        ClassLoader mainClassLoader = SomeClassToLoad.class.getClassLoader();

        System.out.println("Here is how it is done with Byte Buddy");
        byte[] newBuf = new ByteBuddy()
                .redefine(SomeClassToLoad.class)
                .method(named("print"))
                .intercept(MethodDelegation.to(Target.class))
                .make().getBytes();
        runTheNewClassBuffer(newBuf);
    }

    public static class Target {

        public static void print() {
            System.out.println("PUM BYTE BUDDY");
        }
    }
}
