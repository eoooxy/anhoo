import com.alibaba.fastjson.JSON;
import com.anhoo.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//        System.out.println("123");

//        Jedis jedis = new Jedis("127.0.0.1", 7777);
//        jedis.auth("eoooxy");
//        System.out.println(jedis.bitcount("bit_1"));

        Map<String, List<Map<String, String>>> hashMap = new HashMap<String, List<Map<String, String>>>();
        Map<String, String> map = new HashMap<>();
        map.put("name", "xueyuan");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(map);
        hashMap.put("hashName", list);

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        template.opsForHash().put("hash", "name", hashMap.toString());
        System.out.println(template.opsForHash().get("hash", "name"));
//        List<Map> mapList = (List<Map>) template.opsForHash().get("hash", "name");
//
//        System.out.println(mapList.size());

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

}
