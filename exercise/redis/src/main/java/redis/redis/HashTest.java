package redis.redis;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class HashTest {

	private Jedis jedis = null;
	
	@Before
	public void init() {
		jedis = new Jedis("192.168.241.128", 6379);
	}
	
	@Test
	public void testAdd() {
		jedis.hset("ll", "xiaomi", "note3");
		jedis.hset("ll", "huawei", "p10");
		jedis.hset("ll", "360", "n6");
		jedis.close();
	}
	
	@Test 
	public void testGet() {
		Map<String, String> phone = jedis.hgetAll("ll");
		Set<Entry<String, String>> entrySet = phone.entrySet();
		for(Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		jedis.close();
	}
}
