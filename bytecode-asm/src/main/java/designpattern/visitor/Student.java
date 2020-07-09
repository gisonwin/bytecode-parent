package designpattern.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Element {
    private String name;//姓名
    private int grade;//成绩
    private int papers;//论文数量
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
