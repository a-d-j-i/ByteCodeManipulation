package main;

import static main.Main.bytesToHex;

public class Example4ByteCodeModification {

    public static void main() throws Exception {
        System.out.println("The plan: reading and parsing the original class file and then...");
        //byte[] buf = readTheClassBytes();
        byte[] newBuf = new byte[]{
            (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE, 0x00, 0x00, 0x00, 0x34, 0x00, 0x20, 0x01, 0x00, 0x14, 0x6D, 0x61, 0x69, 0x6E, 0x2F, 0x53, 0x6F, 0x6D, 0x65, 0x43, 0x6C, 0x61, 0x73, 0x73, 0x54, 0x6F, 0x4C, 0x6F, 0x61, 0x64, 0x07, 0x00, 0x01, 0x01, 0x00, 0x10, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x6C, 0x61, 0x6E, 0x67, 0x2F, 0x4F, 0x62, 0x6A, 0x65, 0x63, 0x74, 0x07, 0x00, 0x03, 0x01, 0x00, 0x06, 0x3C, 0x69, 0x6E, 0x69, 0x74, 0x3E, 0x01, 0x00, 0x03, 0x28, 0x29, 0x56, 0x01, 0x00, 0x04, 0x43, 0x6F, 0x64, 0x65, 0x01, 0x00, 0x0F, 0x4C, 0x69, 0x6E, 0x65, 0x4E, 0x75, 0x6D, 0x62, 0x65, 0x72, 0x54, 0x61, 0x62, 0x6C, 0x65, 0x01, 0x00, 0x12, 0x4C, 0x6F, 0x63, 0x61, 0x6C, 0x56, 0x61, 0x72, 0x69, 0x61, 0x62, 0x6C, 0x65, 0x54, 0x61, 0x62, 0x6C, 0x65, 0x01, 0x00, 0x04, 0x74, 0x68, 0x69, 0x73, 0x01, 0x00, 0x16, 0x4C, 0x6D, 0x61, 0x69, 0x6E, 0x2F, 0x53, 0x6F, 0x6D, 0x65, 0x43, 0x6C, 0x61, 0x73, 0x73, 0x54, 0x6F, 0x4C, 0x6F, 0x61, 0x64, 0x3B, 0x07, 0x00, 0x03, 0x0C, 0x00, 0x05, 0x00, 0x06, 0x0A, 0x00, 0x0C, 0x00, 0x0D, 0x01, 0x00, 0x05, 0x70, 0x72, 0x69, 0x6E, 0x74, 0x01, 0x00, 0x10, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x6C, 0x61, 0x6E, 0x67, 0x2F, 0x53, 0x79, 0x73, 0x74, 0x65, 0x6D, 0x07, 0x00, 0x10, 0x01, 0x00, 0x03, 0x6F, 0x75, 0x74, 0x01, 0x00, 0x15, 0x4C, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x69, 0x6F, 0x2F, 0x50, 0x72, 0x69, 0x6E, 0x74, 0x53, 0x74, 0x72, 0x65, 0x61, 0x6D, 0x3B, 0x0C, 0x00, 0x12, 0x00, 0x13, 0x09, 0x00, 0x11, 0x00, 0x14, 0x01, 0x00, 0x03, 0x50, 0x55, 0x4D, 0x08, 0x00, 0x16, 0x01, 0x00, 0x13, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x69, 0x6F, 0x2F, 0x50, 0x72, 0x69, 0x6E, 0x74, 0x53, 0x74, 0x72, 0x65, 0x61, 0x6D, 0x07, 0x00, 0x18, 0x01, 0x00, 0x07, 0x70, 0x72, 0x69, 0x6E, 0x74, 0x6C, 0x6E, 0x01, 0x00, 0x15, 0x28, 0x4C, 0x6A, 0x61, 0x76, 0x61, 0x2F, 0x6C, 0x61, 0x6E, 0x67, 0x2F, 0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x3B, 0x29, 0x56, 0x0C, 0x00, 0x1A, 0x00, 0x1B, 0x0A, 0x00, 0x19, 0x00, 0x1C, 0x01, 0x00, 0x0A, 0x53, 0x6F, 0x75, 0x72, 0x63, 0x65, 0x46, 0x69, 0x6C, 0x65, 0x01, 0x00, 0x14, 0x53, 0x6F, 0x6D, 0x65, 0x43, 0x6C, 0x61, 0x73, 0x73, 0x54, 0x6F, 0x4C, 0x6F, 0x61, 0x64, 0x2E, 0x6A, 0x61, 0x76, 0x61, 0x00, 0x21, 0x00, 0x02, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x01, 0x00, 0x05, 0x00, 0x06, 0x00, 0x01, 0x00, 0x07, 0x00, 0x00, 0x00, 0x2F, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00, 0x05, 0x2A, (byte) 0xB7, 0x00, 0x0E, (byte) 0xB1, 0x00, 0x00, 0x00, 0x02, 0x00, 0x08, 0x00, 0x00, 0x00, 0x06, 0x00, 0x01, 0x00, 0x00, 0x00, 0x07, 0x00, 0x09, 0x00, 0x00, 0x00, 0x0C, 0x00, 0x01, 0x00, 0x00, 0x00, 0x05, 0x00, 0x0A, 0x00, 0x0B, 0x00, 0x00, 0x00, 0x01, 0x00, 0x0F, 0x00, 0x06, 0x00, 0x01, 0x00, 0x07, 0x00, 0x00, 0x00, 0x15, 0x00, 0x02, 0x00, 0x01, 0x00, 0x00, 0x00, 0x09, (byte) 0xB2, 0x00, 0x15, 0x12, 0x17, (byte) 0xB6, 0x00, 0x1D, (byte) 0xB1, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x1E, 0x00, 0x00, 0x00, 0x02, 0x00, 0x1F,};
        System.out.println("We construct a new buffer with a new class in it and run it trough our own ClassLoader");
        runTheNewClassBuffer(newBuf);
    }

    public static void runTheNewClassBuffer(byte[] newBuf) throws Exception {
        System.out.println("New buffer: " + newBuf.length + " " + bytesToHex(newBuf));
        int newLen = newBuf.length;
        ClassLoader mainClassLoader = SomeClassToLoad.class.getClassLoader();
        ClassLoader classLoader1 = new ClassLoader(mainClassLoader) {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                if (name.equals("main.SomeClassToLoad")) {
                    Class ret = defineClass("main.SomeClassToLoad", newBuf, 0, newLen);
                    System.out.println("Returning a new class with different behaviour");
                    return ret;
                }
                return super.loadClass(name, resolve);
            }
        };
        Class someClassToLoadClass1 = classLoader1.loadClass("main.SomeClassToLoad");
        System.out.println("Calling the new class mehtod");
        Object otherInstance = someClassToLoadClass1.newInstance();
        someClassToLoadClass1.getDeclaredMethod("print").invoke(otherInstance);
    }
}
