package redis.redis;

import java.util.Random;

import redis.clients.jedis.Jedis;

public class SortedSetTest {
	public static void main(String[] args) throws Exception{
		Jedis jedis = new Jedis("192.168.241.128", 6379);
		
		Random random = new Random();
		String heros[] = {"易大师", "德邦", "剑姬", "盖伦", "阿卡丽", "金克斯", "提莫", "亚索", "劫", "猴子"};
		while(true) {
			int index = random.nextInt(heros.length);
			String hero = heros[index];
			Thread.sleep(2000);
			
			//英雄出场次数加1
			//第一次添加的时候，集合不存在，zincrby方法会创建
			jedis.zincrby("heros", 1, hero);
			System.err.println(hero + "出场了。。。。。。");
		}
	}
}
