package designpattern.visitor;

import java.util.Optional;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public class GradeVisitor implements Visitor {
    @Override
    public void visit(Student student) {
        Optional.of(student).ifPresent(
                stu -> {
                    if (stu.getGrade() > 90) {
                        System.out.printf("%s 's score is %d , its very excellent \n", stu.getName(), stu.getGrade());
                    }
                }
        );
    }

    @Override
    public void visit(Teacher teacher) {
        Optional.of(teacher).ifPresent(
                tea ->{
                    if (tea.getScore()>85) {
                        System.out.printf("%s 's score is %d , its very excellent", tea.getName(), tea.getScore());

                    }
                }
        );
    }
}
