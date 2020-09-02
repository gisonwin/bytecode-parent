package charles.crack;

import javassist.*;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @date 2020/5/25 15:25
 */
public class Course {
    //javassist 创建新类,并添加一个方法以及成员变量,成员变量的getter/setter
    static void createClassAndAddMethod(String className, String methodSrc, String target) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass(className);
//        //工厂方法创建一个hello方法.
        CtField ctField = CtField.make("public String name;", cc);
        cc.addField(ctField);
        CtMethod getter = CtNewMethod.getter("getName", ctField);
        CtMethod setter = CtNewMethod.setter("setName", ctField);

        CtMethod hello = CtNewMethod.make(methodSrc, cc);
        cc.addMethod(getter);
        cc.addMethod(setter);
        cc.addMethod(hello);
        cc.writeFile(target);
    }

    //创建一个新接口,并添加接口中的方法
    static void createInterfaceAndAddMethod(String interfaceParentName, String interfaceName, String target) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass shape = pool.makeInterface(interfaceParentName);//创建父类接口
        CtClass[] params = new CtClass[]{CtClass.booleanType, CtClass.longType};
        CtMethod ctMethod = CtNewMethod.abstractMethod(pool.get("void"), "draw", params, null, shape);

        shape.addMethod(ctMethod);
        shape.writeFile(target);//生成父类接口文件
        CtClass cc = pool.makeInterface(interfaceName, shape);//创建子类接口


        cc.writeFile(target);


    }


    public static void main(String[] args) throws Exception {
        String className = "HelloCreatedByJavassist";
        String methodSrc = "public void sayhi(){System.out.println(\"hello world!\");}";
        String target = Thread.currentThread().getContextClassLoader().getResource(".").getFile() + "test/javassist";

        String interfaceParentName = "Shape";
        String interfaceName = "Circle";

//        createClassAndAddMethod(className,methodSrc,target);
        createInterfaceAndAddMethod(interfaceParentName, interfaceName, target);
        System.out.println("javassist end");
    }
}
