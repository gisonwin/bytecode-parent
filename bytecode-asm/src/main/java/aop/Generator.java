package aop;

import lombok.Cleanup;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

public class Generator {

    public static void main(String[] args) throws Exception {
        //使用全限定名读取字节码源文件
        String source = HelloWorld.class.getName();
        ClassReader reader = new ClassReader(source/*"aop.HelloWorld"*/);
        //创建classWriter,并设置系统自动计算栈和本地变量
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //创建自定义Visitor,后续ClassReader遍历
        ClassVisitor visitor = new AopClassVisitor(Opcodes.ASM8, writer);
        //开始扫描class文件.
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        //生成全新的字节码
        byte[] data = writer.toByteArray();
        String dir = Thread.currentThread().getContextClassLoader().getResource(".").getFile();
        //  将字节码写到该类原来生成的位置,即覆盖原有字节码文件.
        File file = new File(dir + source.replace(".", "/") + ".class");
        @Cleanup FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        System.out.println("ASM end");

    }
}