package designpattern.visitor;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public interface Visitor {
        void visit(Student student);
        void visit(Teacher teacher);
}
