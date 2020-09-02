package api;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.util.Objects;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see String
 * @since 2020/7/27
 */
public class Main {
    //当前类的class path
    static String dir = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(".")).getFile();
    static void create() throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        //创建接口
        CtClass interfaceA = classPool.makeInterface("InterfaceA");
        //创建接口的 public abstract 方法
        CtMethod interfaceMethod = CtNewMethod.abstractMethod(CtClass.voidType,"method",new CtClass[]{CtClass.longType,CtClass.intType},null,interfaceA);
       //接口添加方法
        interfaceA.addMethod(interfaceMethod);
        //生成字节码文件
        interfaceA.writeFile(dir+"/api/");
        //创建类
        CtClass clazzA = classPool.makeClass("ClazzA");
        //设置类的接口
        clazzA.setInterfaces(new CtClass[]{interfaceA});
        //CtNewMethod工厂方法添加一个新方法
        CtMethod make = CtNewMethod.make("public void method(long longVal,int intVal) { System.out.println(longVal);}", clazzA);

        clazzA.addMethod(make);
        clazzA.writeFile(dir+"/api/");

    }

    static void modify() throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("api.Main");
        ctClass.setSuperclass(classPool.get("java.util.List"));
        ctClass.setInterfaces(new CtClass[]{classPool.get("java.io.Serializable")});
        ctClass.writeFile(dir);
    }

    public static void main(String[] args) throws Exception {
        modify();
        create();

    }

}
