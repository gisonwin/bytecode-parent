import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @date 2020/5/26 10:37
 */
public class Test {
    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        String classPath = Thread.currentThread().getContextClassLoader().getResource(".").getFile();
        pool.insertClassPath(classPath);
        CtClass cc = pool.get("test.javassist.Hello");
        CtMethod cm = cc.getDeclaredMethod("say");
        cm.insertBefore("{ System.out.println(\"insert a method before\");}");
        Class<?> clazz = cc.toClass();
        Hello hello = (Hello)clazz.newInstance();
        hello.say();
    }
}
