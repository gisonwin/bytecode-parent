package treeapi;

import jdk.internal.org.objectweb.asm.Type;
import lombok.Cleanup;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 * @since 2020/7/16
 */
public class GeneratorClazz {
    public static void main(String[] args) throws Exception {
        String packageName = "treeapi";
        String clazzName = "TreeTest";
        String nodeName = packageName + "/" + clazzName;
        ClassWriter writer = new ClassWriter(Opcodes.ASM8);
        ClassNode node = gen();
        node.accept(writer);
        String dir = Thread.currentThread().getContextClassLoader().getResource(".").getFile();
        File file = new File(dir + nodeName + ".class");
        @Cleanup FileOutputStream fout = new FileOutputStream(file);
        fout.write(writer.toByteArray());
        System.out.println(LocalDateTime.now().toString() + " success");
    }

    private static ClassNode gen() {
        ClassNode node = new ClassNode();
        //版本
        node.version = Opcodes.V1_8;
        //类的描述符 公共的抽象类
        node.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT;
        //类的名称
        node.name = "treeapi/TreeTest";
        //它的父类
        node.superName = Type.getDescriptor(Object.class);
        //它的接口
        node.interfaces.add(Type.getDescriptor(Serializable.class));
        //定义一个成员变量
        FieldNode fieldNode = new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "radius", "D", null, new Double(3.456));
        node.fields.add(fieldNode);
        //定义一个抽象方法
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, new String[]{"java/lang/Exception"});
        MethodNode node2 = new MethodNode(Opcodes.ACC_PUBLIC, "test", "()V", null, null);
        node.methods.add(methodNode);
        node.methods.add(node2);

        node2.visitFieldInsn(Opcodes.GETSTATIC, org.objectweb.asm.Type.getInternalName(System.class), "out", org.objectweb.asm.Type.getDescriptor(PrintStream.class));
        node2.visitLdcInsn("Now is " + LocalDateTime.now().toString());
        node2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, org.objectweb.asm.Type.getInternalName(PrintStream.class), "println", "(Ljava/lang/String;)V", false);
//        InsnList insnList = node2.instructions;
//        insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, org.objectweb.asm.Type.getInternalName(System.class), "out", org.objectweb.asm.Type.getDescriptor(PrintStream.class)));
//        insnList.add(new LdcInsnNode("Now is " + LocalDateTime.now().toString()));
//        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, org.objectweb.asm.Type.getInternalName(PrintStream.class), "println", "(Ljava/lang/String;)V", false));
        node2.maxLocals=2;
        node2.maxStack=2;
        node2.visitEnd();

        return node;
    }
}
