/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/26
 * Time  15:56
 */

public class XPlace implements Place {
    @Override
    public BallPen ballPen(String s) {
        return new BallPen(s);
    }
    @Override
    public Pencil pencil(String s) {
        return new Pencil(s);
    }
}
