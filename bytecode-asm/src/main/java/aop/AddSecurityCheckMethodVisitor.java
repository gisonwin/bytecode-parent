package aop;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.PrintStream;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @date 2020/5/22 17:50
 */
public class AddSecurityCheckMethodVisitor extends MethodVisitor {
    public AddSecurityCheckMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM8, mv);
    }
    //进入方法之后 立即执行的语句,这部分内容的修改在加载源方法字节码之前.
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "test/asm/Generator", "check", "()V", false);
    }
    //在该方法RETURN 前执行的方法.通过判断当前指令为RETURN时,表明即将执行return语句,此时插入的字节码内容.
    //此处是执行System.out.println(LocalDateTime.now().toString())
    public void visitInsn(int opcode) {
        if (Opcodes.RETURN == opcode) {
            //tips:以下三个步骤仅仅是实现了System.out.println(LocalDateTime.now().toString())
//            获取System静态成员out，其对应的指令为getstatic
            String owner = Type.getInternalName(System.class);//java/lang/System
            String name="out";
            String descriptor = Type.getDescriptor(PrintStream.class);//Ljava/io/PrintStream;
            visitFieldInsn(Opcodes.GETSTATIC, owner, name, descriptor);
            //获取要打印的字符串常量(当前时间) ,JVM指令为ldc
            visitLdcInsn(LocalDateTime.now().toString());
            //获取PrintStream.println(String)方法,指令为invokevirtual
            owner = Type.getInternalName(PrintStream.class);//java/io/PrintStream
            name="println";
            descriptor = Type.getDescriptor(String.class);//Ljava/lang/String;
            //V 是指的Void
            visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, name, "("+descriptor+")V", false);
        }
        super.visitInsn(opcode);
    }
}
