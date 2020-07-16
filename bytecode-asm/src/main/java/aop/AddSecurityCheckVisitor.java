package aop;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.Objects;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 */
public class AddSecurityCheckVisitor extends ClassVisitor {

    public AddSecurityCheckVisitor(int api) {
        super(api);
    }

    public AddSecurityCheckVisitor(int api, ClassVisitor classVisitor) {
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
        MethodVisitor wrappedMv = mv;
        if (!Objects.isNull(mv)) {
            if (name.equals("hello")) {
                wrappedMv = new AddSecurityCheckMethodVisitor(mv);
            }

        }

        return wrappedMv;
    }
}
