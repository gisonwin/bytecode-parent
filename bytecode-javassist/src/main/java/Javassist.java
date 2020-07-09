import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @date 2020/5/8 17:22
 */
public class Javassist {
    public static ClassPool pool = ClassPool.getDefault();
    public static String workspacePath = "D:";

    public static void main(String[] args) throws Exception {

        pool.insertClassPath(workspacePath + "\\charles.jar");
        //从类型池中读取指定类型
        CtClass ctClass = pool.get("com.xk72.charles.Bvcn");
        //获取指定的方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("InuZ");
        //修改该方法的返回值
        ctMethod.setBody("return \"By Gisonwin(mailto:gisonwin@qq.com)\";");
        //获取全部的构造方法
        CtConstructor[] declaredConstructors = ctClass.getDeclaredConstructors();
        //修改构造方法
        declaredConstructors[0].setBody("{this.Gwbn = \"By Gisonwin(gisonwin@qq.com)\";\n" +
                "    this.InuZ = true;} ");
        declaredConstructors[1].setBody("{this.Gwbn = \"By Gisonwin(gisonwin@qq.com)\";\n" +
                "    this.InuZ = true;}");

        //最后将写好的类写入到指定的工作空间中
        ctClass.writeFile(workspacePath);

    }
}
