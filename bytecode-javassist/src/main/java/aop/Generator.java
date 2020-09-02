package aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.net.URL;
import java.util.Objects;

public class Generator {
    public static void main(String[] args) throws Exception {
        //得到当前classloader的路径
        URL resource = Thread.currentThread().getContextClassLoader().getResource(".");
        //得到路径的字符串形式
        String dir = Objects.requireNonNull(resource).getFile();
        System.out.println(dir);
        //首先获得class pool对象
        ClassPool classPool = ClassPool.getDefault();
        //得到hello world ctclass对象，get的参数为全限定名称。
        CtClass ctClass = classPool.get("aop.HelloWorld");
        //通过class对象得到方法对象。方法名称hello
        CtMethod hello = ctClass.getDeclaredMethod("hello");
        //class pool获得Check的ct class对象
        CtClass checkClass = classPool.get("aop.Check");
        //根据Check 对象拿 到check方法对象
        CtMethod checkMethod = checkClass.getDeclaredMethod("check");
        //得到check方法对象的方法名称。
        String check = checkMethod.getName();
        //方法前插入一些代码
        StringBuffer builder = new StringBuffer();
        //（$$)代表当前方法的所有参数，一定要注意;，如果没写，在运行时会提示缺少；。
        builder.append("aop.Check." + check + "($$);\n");
        String before = builder.toString();
        System.out.println(before);
        //aop的前置插入
        hello.insertBefore(before);
//        //方法之后插入一些代码
        String now = "System.out.println(\"Now is \" + java.time.LocalDateTime.now().toString());\n";
        //aop的后置插入
        hello.insertAfter(now);
        //将当前ctclass对象覆盖掉原来字节码文件。
        ctClass.writeFile(dir);
        System.out.println("javassist write file complete");

    }
}
