package charles.crack;

import javassist.*;

import java.io.File;

public class CharlesCrack {
    static final String workspacePath = "D:\\Charles";
    static String classType = "com.xk72.charles.Bvcn";
    static String methodName = "InuZ";
    static String registerShow = "\"Yzmedu.com(by gisonwin)\"";

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        pool.insertClassPath(workspacePath + File.separator + "charles.jar");
        //从类型池中读取指定类型
        CtClass ctClass = pool.get(classType);
        //获取指定的方法
        CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
        //修改该方法的返回值
        ctMethod.setBody("return " + registerShow + ";");
        //获取全部的构造方法
        CtConstructor[] declaredConstructors = ctClass.getDeclaredConstructors();
        //修改构造方法
        declaredConstructors[0].setBody("{this.Gwbn = " + registerShow + ";\n" +
                "    this.InuZ = true;} ");
        declaredConstructors[1].setBody("{this.Gwbn = " + registerShow + ";\n" +
                "    this.InuZ = true;}");
        declaredConstructors[2].insertAfter("{this.Gwbn = " + registerShow + ";\n" +
                "    this.InuZ = true;}");
        //最后将写好的类写入到指定的工作空间中
        ctClass.writeFile(workspacePath);
    }
}
