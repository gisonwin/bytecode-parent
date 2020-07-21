package aop;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.Objects;

public class AopClassVisitor extends ClassVisitor {

    public AopClassVisitor(int api) {
        super(api);
    }

    public AopClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    //重写visit method方法,访问到 hello 方法
    public MethodVisitor visitMethod(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (!Objects.isNull(mv)) {
            if (name.equals("hello")) {
                return new AopMethodVisitor(mv);
            }
        }
        return mv;
    }
}