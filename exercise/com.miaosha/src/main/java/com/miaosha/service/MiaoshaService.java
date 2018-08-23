package com.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miaosha.domain.MiaoshaOrder;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.domain.OrderInfo;
import com.miaosha.redis.MiaoshaKey;
import com.miaosha.redis.RedisService;
import com.miaosha.util.MD5Util;
import com.miaosha.util.UUIDUtil;
import com.miaosha.vo.GoodsVo;

@Service
public class MiaoshaService {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	RedisService redisService;

	@Transactional
	public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
		// 减库存
		boolean success = goodsService.reduceStock(goods);
		if (success) {
			// 下订单 写入秒杀订单
			return orderService.createOrder(user, goods);
		} else {
			setGoodsOver(goods.getId());
			return null;
		}
	}

	public long getMiaoshaResult(Long userId, long goodsId) {
		MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
		if (order != null) {
			// 秒杀成功
			return order.getOrderId();
		} else {
			boolean isOver = getGoodsOver(goodsId);
			if (isOver) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	private void setGoodsOver(Long goodsId) {
		redisService.set(MiaoshaKey.isGoodsOver, "" + goodsId, true);
	}

	private boolean getGoodsOver(long goodsId) {
		return redisService.exists(MiaoshaKey.isGoodsOver, "" + goodsId);
	}

	public boolean checkPath(MiaoshaUser user, long goodsId, String path) {
		if(user == null || path == null) {
			return false;
		}
		String pathOld = redisService.get(MiaoshaKey.getMiaoshaPath, ""+user.getId() + "_"+ goodsId, String.class);
		return path.equals(pathOld);
	}
	
	public String createMiaoshaPath(MiaoshaUser user, long goodsId) {
		if(user == null || goodsId <= 0) {
			return null;
		}
		String string = MD5Util.md5(UUIDUtil.uuid() + "123456");
		redisService.set(MiaoshaKey.getMiaoshaPath, "" + user.getId() + "_" + goodsId, string);
		return string;
	}
}
