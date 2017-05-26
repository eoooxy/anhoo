/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/26
 * Time  15:57
 */

public class YPlace implements Place {

    @Override
    public BallPen ballPen(String s) {
        return new BallPen(s);
    }
    @Override
    public Pencil pencil(String s) {
        return new Pencil(s);
    }
}
