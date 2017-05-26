import com.alibaba.fastjson.JSON;
import com.anhoo.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/09
 * Time  13:56
 */


public class RedisString {


    @Test
    public void redis() {

        Map<String, List<Map<String, String>>> hashMap = new HashMap<String, List<Map<String, String>>>();
        Map<String, String> map = new HashMap<>();
        map.put("name", "xueyuan");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(map);
        hashMap.put("hashName", list);

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        template.opsForValue().set("name", "eoooxy");
        template.opsForHash().put("hash", "name", hashMap.toString());
        System.out.println(template.opsForValue().get("name"));
        System.out.println(template.opsForHash().get("hash", "name"));
    }

    @Test
    public void json2entity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("123");
        userEntity.setPassWord("123");
        String str = JSON.toJSONString(userEntity);
        System.out.println(str);

        UserEntity entity = JSON.parseObject(str, UserEntity.class);
        System.out.println(entity.getUserName() + "--" + entity.getPassWord());
    }

    @Test
    public void autoDelKey() throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        System.out.println("设置Key:name的值且存活时间为20s:");
        template.opsForValue().set("name", "admin", 20, TimeUnit.SECONDS);
        System.out.println("当前的Key:name对应的Value为：" + template.opsForValue().get("name"));
        System.out.println("等待20s ……");
        //Thread.sleep(20000);
        System.out.println("20s到了，当前的Key:name对应的Value是否存在：" + template.hasKey("name") + ",值为：" + template.opsForValue().get("name"));
    }

    @Test
    public void connectDb() throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/anhoo";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = connection.prepareStatement("CREATE DATABASE databasename");
        int result = ps.executeUpdate();
        System.out.println(result);
    }

    static Logger logger = LogManager.getLogger(RedisString.class);

    @Test
    public void log4j() {
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
        System.out.println("Hello World!");
    }

    @Test
    public void string() {
        String str = "Hello J@@@ World!";
        System.out.println(str.split("@@@")[0]);
        System.out.println(str.indexOf("Java"));
        System.out.println(str.indexOf("3123"));
    }


    @Test
    public void factory() {
//        System.out.println("=============================== 简单工厂 ==================================");
////      简单工厂
//        //首先我们找到了制造圆珠笔的厂家
//        CreateBallPen createBallPen = new CreateBallPen();
//        //告知 批发商要得力牌子的圆珠笔
//        BallPen dl = createBallPen.createBallPen("dl");
//        dl.method();
//        //告知 批发商要晨光牌子的圆珠笔
//        BallPen cg = createBallPen.createBallPen("cg");
//        cg.method();


//        System.out.println("=============================== 工厂方法 ==================================");
////     工厂方法
//        //有商家需要铅笔,先告知铅笔厂,我们需要制作铅笔
//        Pencil pencil = new CreatePencil().create();
//        //告诉铅笔厂，我们需要生产的品牌是得力。
//        pencil.dl();
//        //有商家需要圆珠笔
//        BallPen ballPen = new CreateBallPen().create();
//        ballPen.cg();
//
        System.out.println("=============================== 抽象工厂 ==================================");
//        抽象工厂
//        X地方的 铅笔和圆珠笔
        Place x = new XPlace();
        BallPen ballPen = x.ballPen("X");
        ballPen.dl();
        Pencil pencill = x.pencil("X");
        pencill.cg();
//        Y地方的 铅笔和圆珠笔
        Place y = new YPlace();
        BallPen yballPen = y.ballPen("Y");
        yballPen.dl();
        Pencil ypencill = y.pencil("Y");
        ypencill.cg();
    }


}
