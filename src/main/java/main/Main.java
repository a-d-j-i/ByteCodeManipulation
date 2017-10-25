package main;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Byte code modification can be done on compile time, post compile, or runtime");
        System.out.println("Here we want to talk about runtime modifications");
        System.out.println("We need tu understand reflection and classloader stuff");

        System.out.println("------------------- EXAMPLE 1");
        Example1ReflectionAndClassLoader.main();
        System.out.println("------------------- EXAMPLE 2");
        Example2ClassCastException.main();
        System.out.println("------------------- EXAMPLE 3");
        Example3JClassLib.main();
        System.out.println("------------------- EXAMPLE 4");
        Example4ByteCodeModification.main();
        System.out.println("------------------- EXAMPLE 5");
        Example5ASM.main();
        System.out.println("------------------- EXAMPLE 6");
        Example6Javassist.main();
        System.out.println("------------------- EXAMPLE 7");
        Example7ByteBuddy.main();

    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
