package aop;

import lombok.Cleanup;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @date 2020/5/22 17:38
 */
public class Generator {
    public static void check() {
        System.out.println("Generator.check method invoked ...");
    }

    public static void main(String[] args) throws Exception {
        //使用全限定名读取字节码源文件
        ClassReader reader = new ClassReader("test.asm.HelloWorld");
        //创建classWriter,并设置系统自动计算栈和本地变量
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //创建自定义Visitor,后续ClassReader遍历
        ClassVisitor visitor = new AddSecurityCheckVisitor(Opcodes.ASM8, writer);
        //开始扫描class文件.
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        //生成全新的字节码
        byte[] data = writer.toByteArray();
        String file1 = Thread.currentThread().getContextClassLoader().getResource(".").getFile();
        //  将字节码写到该类原来生成的位置,即覆盖原有字节码文件.
        File file = new File(file1 + "test/asm/" + "HelloWorld.class");
        @Cleanup FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        System.out.println("ASM end");

    }
}
