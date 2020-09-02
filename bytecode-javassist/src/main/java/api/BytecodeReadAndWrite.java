package api;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public class BytecodeReadAndWrite {
    static void howToUseCtClass() throws Exception {
        String dir = Thread.currentThread().getContextClassLoader().getResource(".").getFile();
        ClassPool classPool = ClassPool.getDefault();
        //得到当前字节码文件对象
        CtClass ctClass = classPool.get("api.Main");
        //设置父类
        ctClass.setSuperclass(classPool.get("java.util.List"));
        //设置接口.
        ctClass.setInterfaces(new CtClass[]{classPool.get("java.io.Serializable")});
        //将字节码文件对象写回原处.
        ctClass.writeFile(dir);

    }
    public static void main(String[] args) {
//        howToUseCtClass();
    }
}
