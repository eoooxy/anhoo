import com.alibaba.fastjson.JSON;
import com.anhoo.entity.UserEntity;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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

    static Logger logger = LogManager.getLogger(RedisString.class);

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

    @Test
    public void base() throws IOException {
        String s = "Hello Java !!";
        System.out.println("加密前的数据：" + s);
        System.out.println("==============sun.misc.BASE64Decoder==============");
        String s2 = new BASE64Encoder().encode(s.getBytes());
        System.out.println("加密后的数据：" + s2);
        byte[] s3 = new BASE64Decoder().decodeBuffer(s2.toString());
        System.out.println("解密后的数据：" + new String(s3));

        System.out.println("==============java.util.Base64==============");
        String ss2 = Base64.getEncoder().encodeToString(s.getBytes());
        System.out.println("Basic加密后的数据：" + ss2);
        byte[] ss3 = Base64.getDecoder().decode(ss2);
        System.out.println("Basic解密后的数据：" + new String(ss3));

        String sss2 = Base64.getUrlEncoder().encodeToString(s.getBytes());
        System.out.println("URL加密后的数据 ：" + sss2);
        byte[] sss3 = Base64.getUrlDecoder().decode(sss2);
        System.out.println("URL解密后的数据 ：" + new String(sss3));

        String ssss2 = Base64.getMimeEncoder().encodeToString(s.getBytes());
        System.out.println("MIME加密后的数据：" + ssss2);
        byte[] ssss3 = Base64.getMimeDecoder().decode(ssss2);
        System.out.println("MIME解密后的数据：" + new String(ssss3));
    }

    @Test
    public void md() throws NoSuchAlgorithmException {
        //SHA-1 MD5 SHA-256
        String s = "Hello Java !!";
        System.out.println("加密前的数据：" + s);
        MessageDigest md = MessageDigest.getInstance("MD5");
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        MessageDigest sha256 = MessageDigest.getInstance("SHA-1");
        md.update(s.getBytes());
        sha1.update(s.getBytes());
        sha256.update(s.getBytes());
        BigInteger bigInteger1 = new BigInteger(md.digest());
        BigInteger bigInteger2 = new BigInteger(sha1.digest());
        BigInteger bigInteger3 = new BigInteger(sha256.digest());
        System.out.println("MD5  为:" + bigInteger1.toString());
        System.out.println("SHA-1为:" + bigInteger2.toString());
        System.out.println("SHA-256:" + bigInteger3.toString());
    }

    @Test
    public void des() throws Exception {

        String s = "Hello Java!";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        keyGenerator.init(56);//size
//        keyGenerator.init(168);//size
//        keyGenerator.init(128);//size  128 192 256

        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);

        System.out.println("desKeySpec:" + Hex.encodeHexString(desKeySpec.getKey()));

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
//        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
//        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("AES");
        SecretKey generateKey = secretKeyFactory.generateSecret(desKeySpec);


        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey);
        byte[] resultBytes = cipher.doFinal(s.getBytes());
        String s2 = Hex.encodeHexString(resultBytes);
        System.out.println(s2);
        cipher.init(Cipher.DECRYPT_MODE, generateKey);
        byte[] result = Hex.decodeHex(s2.toCharArray());
        System.out.println(new String(cipher.doFinal(result)));
    }

    //    非对称加密
    @Test
    public void rsa() throws Exception {

        String s = "Hello Java!";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println("rsaPublicKey:" + Hex.encodeHexString(rsaPublicKey.getEncoded()));
        System.out.println("rsaPrivateKey:" + Hex.encodeHexString(rsaPrivateKey.getEncoded()));

        //私钥加密 公钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec
                = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] resultBytes = cipher.doFinal(s.getBytes());

        //私钥解密 公钥加密
//          X509EncodedKeySpec x509EncodedKeySpec =
//                  new X509EncodedKeySpec(rsaPublicKey.getEncoded());
//          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//          PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
//          Cipher cipher = Cipher.getInstance("RSA");
//          cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//          byte[] resultBytes = cipher.doFinal(src.getBytes());

        String s2 = Hex.encodeHexString(resultBytes);
        System.out.println(s2);

        //私钥加密 公钥解密
        X509EncodedKeySpec x509EncodedKeySpec =
                new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
//        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] resultBytes2 = cipher.doFinal(Hex.decodeHex(s2.toCharArray()));

        //私钥解密 公钥加密
//          PKCS8EncodedKeySpec pkcs8EncodedKeySpec
//              = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
//          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//          PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//          Cipher cipher = Cipher.getInstance("RSA");
//          cipher.init(Cipher.DECRYPT_MODE, privateKey);
//          byte[] resultBytes = cipher.doFinal(Hex.decodeHex(src.toCharArray()));
        System.out.println(new String(resultBytes2));
    }

    @Test
    public void luanma(){
        System.out.println("私钥加密 公钥解密");
    }

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        template.opsForValue().set("b", "b");
        System.out.println(template.opsForValue().get("a"));
    }

}
