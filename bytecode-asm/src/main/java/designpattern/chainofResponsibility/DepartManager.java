package designpattern.chainofResponsibility;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public class DepartManager extends Handler {//部门经理
    @Override
    void process(Staff staff) {
        if (staff.getDays() <= 7) {
            System.out.printf("%s ask for leave for %d days , Approved by %s \n", staff.getName(),
                    staff.getDays(), this.getClass().getSimpleName());
        } else {
            this.handler.process(staff);
        }
    }
}
