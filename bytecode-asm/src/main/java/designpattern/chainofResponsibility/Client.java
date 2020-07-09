package designpattern.chainofResponsibility;

/**
 * {@link }
 *
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 * @see
 */
public class Client {
    public static void main(String[] args) {
        //具体实现者
        TeamLeader teamLeader = new TeamLeader();
        ProgramManager programManager = new ProgramManager();
        DepartManager departManager = new DepartManager();
        CEO ceo = new CEO();
        //创建职责链
        teamLeader.setHandler(programManager);//teamLeader作为职责链入口
        programManager.setHandler(departManager);
        departManager.setHandler(ceo);//CEO作为职责链的结束
        //创建员工
        Staff gison = Staff.builder().name("gison").days(1).build();
        teamLeader.process(gison);
        Staff tom = Staff.builder().name("Tom").days(2).build();
        teamLeader.process(tom);
        Staff white = Staff.builder().name("White").days(5).build();
        teamLeader.process(white);
        Staff jordan = Staff.builder().name("Jordan").days(10).build();
        teamLeader.process(jordan);
    }
}
