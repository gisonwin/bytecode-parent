package designpattern.chainofResponsibility;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public class CEO extends Handler {//CEO,这是职责链的结束,也是默认处理.
    @Override
    void process(Staff staff) {
        System.out.printf("%s ask for leave for %d days , Approved by %s \n", staff.getName(),
                staff.getDays(), this.getClass().getSimpleName());
    }
}
