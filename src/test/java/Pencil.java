/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/26
 * Time  14:02
 */

public class Pencil implements Pen {
    private  String s ;
    public Pencil(String s ) {
        this.s = s;
    }
    public void dl() {
        System.out.println("====================>"+s+"地，开始生产得力牌的铅笔");
    }
    public void cg() {
        System.out.println("====================>"+s+"开始生产晨光牌的铅笔");
    }
}
