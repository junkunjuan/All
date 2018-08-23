package redis.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class SortedSetTest2 {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("192.168.241.128", 6379);
		int i = 1;
		while(true) {
			Thread.sleep(3000);
			System.out.println("第" + i + "次查看榜单-------------");
			
			Set<Tuple> tuples = jedis.zrevrangeWithScores("heros", 0, 5);
			
			for(Tuple tuple : tuples) {
				System.err.println(tuple.getElement() + "  次数：" + tuple.getScore());
			}
			i++;
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}
