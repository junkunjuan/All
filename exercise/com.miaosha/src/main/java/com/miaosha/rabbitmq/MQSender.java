package com.miaosha.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaosha.redis.RedisService;

@Service
public class MQSender {
	
	@Autowired
	AmqpTemplate amqpTemplate;
	
	public void sendMiaoshaMessage(Object message) {
		String msg = RedisService.beanToString(message);
		amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
	}
}
