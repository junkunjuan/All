package redis.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class StringJedisTest {
	private Jedis jedis = null;
	
	@Before
	public void init() {
		jedis = new Jedis("192.168.241.128", 6379);
	}
	
	@Test
	public void testString() {
		jedis.set("01", "lsk");
		jedis.set("02", "邓仁宁");
		
		String u1 = jedis.get("01");
		String u2 = jedis.get("02");
		
		System.out.println(u1);
		System.out.println(u2);
	}
	
	/**
	 * 将对象缓存到redis的String数据结构中
	 * @throws Exception
	 */
	@Test
	public void testObjectCache() throws Exception {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setName("lsk");
		productInfo.setDescription("加油");
		productInfo.setPrice(8888888.888888);
		
		//将对象序列化成字节数组
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		
		//用对象序列化流来将p对象序列化，然后把序列化之后的二进制数据写到byteArrayOutputStream流中
		objectOutputStream.writeObject(productInfo);
		
		//将ba流转成byte数组
		byte[] pByte = byteArrayOutputStream.toByteArray();
		
		//将对象序列化之后的byte数组存到redis的String数据结构中
		jedis.set("product".getBytes(), pByte);
		
		//根据key从redis中取出对象的byte数据  
		byte[] pBy = jedis.get("product".getBytes());
		
		//将byte数据反序列出对象
		ByteArrayInputStream bInputStream = new ByteArrayInputStream(pBy);
		ObjectInputStream oInputStream = new ObjectInputStream(bInputStream);
		
		//从对象读取流中读取中对象
		ProductInfo pResp = (ProductInfo)oInputStream.readObject();
		
		System.out.println(pResp);
	}
	
	/**
	 * 将对象转成json字符串缓存到redis的String数据结构
	 * 使用Gson
	 */
	@Test
	public void testObjectToJsonCache() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setName("deng");
		productInfo.setDescription("are you ok?");
		productInfo.setPrice(88888.888);
		
		//利用gson将对象转成json串
		Gson gson = new Gson();
		String pJson = gson.toJson(productInfo);
		
		//将json串缓存到redis中
		jedis.set("nice", pJson);
		
		//从redis中取出对象的json串
		String pJsonResp = jedis.get("nice");
		
		//将返回的json解析成对象
		ProductInfo pResponse = gson.fromJson(pJsonResp, ProductInfo.class);
		
		System.out.println(pResponse);
	}
}
