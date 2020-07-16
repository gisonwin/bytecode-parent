package designpattern.visitor;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public class Client {
    public static void main(String[] args) {
        Element gisonwin = new Student("gisonwin", 92, 5);
        Visitor visitor = new GradeVisitor();
        gisonwin.accept(visitor);
    }
}
