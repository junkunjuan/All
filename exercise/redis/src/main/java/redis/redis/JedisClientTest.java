package redis.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class JedisClientTest {
	public static void main(String[] args) {
		//创建一个jedis客户端对象（redis的客户端连接）
		Jedis client = new Jedis("192.168.241.128", 6379);
		//测试服务器是否连通
		//String resp = client.ping();
		
		//System.out.println(resp);
		
		Pipeline pipeline = client.pipelined();
		pipeline.set("fool", "bar");
		pipeline.zadd("foo", 1, "barow");
		pipeline.zadd("foo", 0, "brain");
		Response<String> pipeString = pipeline.get("fool");
		Response<Set<String>> response = pipeline.zrange("foo", 0, -1);
		pipeline.sync();
		
		String bar = pipeString.get();
		int soseSize = response.get().size();
		Set<String> setBack = response.get();
		System.out.println(bar);
		System.err.println(soseSize);
		Iterator<String> iterator = setBack.iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key);
		}
		
	}
}
