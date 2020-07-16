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
 */
public class GeneratorClazz {
    public static void main(String[] args) throws Exception {
        String packageName = "treeapi";
        String clazzName = "TreeTest";
        String nodeName = packageName + "/" + clazzName;
        ClassWriter writer = new ClassWriter(Opcodes.ASM8);
        ClassNode node = gen(nodeName);
        node.accept(writer);
        String dir = Thread.currentThread().getContextClassLoader().getResource(".").getFile();
        File file = new File(dir + nodeName + ".class");
        @Cleanup FileOutputStream fout = new FileOutputStream(file);
        fout.write(writer.toByteArray());
        System.out.println(LocalDateTime.now().toString() + " success");
    }

    private static ClassNode gen(String nodeName) {
        ClassNode node = new ClassNode();
        //版本
        node.version = Opcodes.V1_8;
        //类的描述符 公共的抽象类
        node.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT;
        //类的名称
        node.name = nodeName;
        //它的父类
        node.superName = Type.getDescriptor(Object.class);
        //它的接口
        node.interfaces.add(Type.getDescriptor(Serializable.class));
        //定义一个成员变量, public static final double radius = 3.456d
        FieldNode fieldNode = new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "radius", "D", null, new Double(3.456));
        node.fields.add(fieldNode);
        //定义一个抽象方法 public abstract int compareTo(Object obj) throws java.lang.Exception;
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, new String[]{"java/lang/Exception"});
        //方法2 public void test(){}
        MethodNode node2 = new MethodNode(Opcodes.ACC_PUBLIC, "test", "()V", null, null);
        node.methods.add(methodNode);
        node.methods.add(node2);
        //指令代码 System.out.println("Now is " + LocalDateTime.now().toString());
        //step 1 GETSTATIC 通知调用System类的静态方法out.得到PrintStream类
        node2.visitFieldInsn(Opcodes.GETSTATIC, org.objectweb.asm.Type.getInternalName(System.class), "out", org.objectweb.asm.Type.getDescriptor(PrintStream.class));
        //step 2 LDC 将字符串常量"Now is " + LocalDateTime.now().toString()压入操作数栈
        node2.visitLdcInsn("Now is " + LocalDateTime.now().toString());
        //step 3 INVOKEVIRTUAL 调用对象的实例方法,根据实例的实际类型进行分派  PrintStream.println()方法
        node2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, org.objectweb.asm.Type.getInternalName(PrintStream.class), "println", "(Ljava/lang/String;)V", false);
//        InsnList insnList = node2.instructions;
//        insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, org.objectweb.asm.Type.getInternalName(System.class), "out", org.objectweb.asm.Type.getDescriptor(PrintStream.class)));
//        insnList.add(new LdcInsnNode("Now is " + LocalDateTime.now().toString()));
//        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, org.objectweb.asm.Type.getInternalName(PrintStream.class), "println", "(Ljava/lang/String;)V", false));
        node2.maxLocals = 2;
        node2.maxStack = 2;
        node2.visitEnd();

        return node;
    }
}
