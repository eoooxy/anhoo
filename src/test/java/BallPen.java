/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/26
 * Time  13:32
 */

public class BallPen implements Pen {
    private String s;
    public BallPen(String s) {
        this.s = s;
    }
    public void dl() {
        System.out.println("====================>" + s + "开始生产得力牌的圆珠笔");
    }
    public void cg() {
        System.out.println("====================>" + s + "开始生产晨光牌的圆珠笔");
    }
}
