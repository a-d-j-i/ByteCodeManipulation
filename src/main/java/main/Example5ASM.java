package main;

import java.io.PrintStream;
import static main.Example2ClassCastException.readTheClassBytes;
import static main.Example4ByteCodeModification.runTheNewClassBuffer;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Type.getDescriptor;
import static org.objectweb.asm.Type.getInternalName;

public class Example5ASM {

    public static void main() throws Exception {
        byte[] buf = readTheClassBytes();

        System.out.println("Here is how it is done with ASM, too complex, not sure even if my code is right");
        final ClassReader classReader = new ClassReader(buf);
        final ClassWriter cw = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classReader.accept(new MyClassVisitor(cw), ClassReader.EXPAND_FRAMES);
        byte[] newBuf = cw.toByteArray();
        runTheNewClassBuffer(newBuf);
    }

    public static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                String signature, String[] exceptions) {

            MethodVisitor v = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("print") && desc.equals("()V")) {
                v = new ReplaceBody(v, (Type.getArgumentsAndReturnSizes(desc) >> 2) - 1);
            }
            return v;
        }

        static class ReplaceBody extends MethodVisitor {

            private final MethodVisitor targetWriter;
            private final int newMaxLocals;

            ReplaceBody(MethodVisitor writer, int newMaxL) {
                // now, we're not passing the writer to the superclass for our radical changes
                super(Opcodes.ASM5);
                targetWriter = writer;
                newMaxLocals = newMaxL;
            }

            // we're only override the minimum to create a code attribute with a sole RETURN
            @Override
            public void visitMaxs(int maxStack, int maxLocals) {
                targetWriter.visitMaxs(0, newMaxLocals);
            }

            @Override
            public void visitCode() {
                targetWriter.visitCode();
                targetWriter.visitFieldInsn(GETSTATIC, getInternalName(System.class), "out", getDescriptor(PrintStream.class));
                targetWriter.visitLdcInsn("PUM WITH ASM");
                targetWriter.visitMethodInsn(INVOKEVIRTUAL, getInternalName(PrintStream.class), "println", "(Ljava/lang/String;)V", false);
                targetWriter.visitInsn(RETURN);
                targetWriter.visitMaxs(2, 2);
                targetWriter.visitEnd();
                //targetWriter.visitInsn(Opcodes.RETURN);// our new code
            }

            @Override
            public void visitEnd() {
                targetWriter.visitEnd();
            }

            // the remaining methods just reproduce meta information,
            // annotations & parameter names
            @Override
            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                return targetWriter.visitAnnotation(desc, visible);
            }

            @Override
            public void visitParameter(String name, int access) {
                targetWriter.visitParameter(name, access);
            }
        }
    }
}
