package designpattern.chainofResponsibility;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public abstract class Handler {
    protected Handler handler;//持有对下家的引用.protected只子类可见
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    abstract void process(Staff staff);

}
