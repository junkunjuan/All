package com.miaosha.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.access.AccessLimit;
import com.miaosha.domain.MiaoshaOrder;
import com.miaosha.domain.MiaoshaUser;
import com.miaosha.rabbitmq.MQSender;
import com.miaosha.rabbitmq.MiaoshaMessage;
import com.miaosha.redis.GoodsKey;
import com.miaosha.redis.RedisService;
import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;
import com.miaosha.service.GoodsService;
import com.miaosha.service.MiaoshaService;
import com.miaosha.service.OrderService;
import com.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	MiaoshaService miaoshaServie;

	@Autowired
	RedisService redisService;

	@Autowired
	MQSender sender;

	private HashMap<Long, Boolean> localOverMap = new HashMap<>();

	/**
	 * 系统初始化
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		if (goodsList == null) {
			return;
		}
		for (GoodsVo goods : goodsList) {
			redisService.set(GoodsKey.getMiaoshaGoodsStock, "" + goods.getId(), goods.getStockCount());
			localOverMap.put(goods.getId(), false); // 对redis进行标记
		}
	}

	@RequestMapping(value = "/{path}/do_miaosha", method = RequestMethod.POST)
	@ResponseBody
	public Result<Integer> doMiaoSha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId,
			@PathVariable("path") String path) {
		if (user == null) {
			return Result.error(CodeMsg.SESSION_ERROR);
		}

		// 验证path
		boolean check = miaoshaServie.checkPath(user, goodsId, path);
		if (!check) {
			return Result.error(CodeMsg.REQUEST_ILLEGAL);
		}

		// 内存标记，减少redis访问
		boolean over = localOverMap.get(goodsId);
		if (over) {
			return Result.error(CodeMsg.MIAO_SHA_OVER);
		}

		// 判断是否已秒杀到
		MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
		if (miaoshaOrder != null) {
			return Result.error(CodeMsg.REPEATE_MIAOSHA);
		}

		// 预减库存
		long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock, "" + goodsId);
		if (stock < 0) {
			return Result.error(CodeMsg.MIAO_SHA_OVER);
		}

		/*
		 * //判断库存 GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId); int
		 * stock = goods.getStockCount(); if(stock <= 0) { return
		 * Result.error(CodeMsg.MIAO_SHA_OVER); }
		 */

		// 入队
		MiaoshaMessage message = new MiaoshaMessage();
		message.setUser(user);
		message.setGoodsId(goodsId);
		sender.sendMiaoshaMessage(message);
		return Result.success(0); // 排队中

		/*
		 * //减库存 下订单 写入秒杀订单 OrderInfo orderInfo = miaoshaServie.miaosha(user,
		 * goods); model.addAttribute("user", user); return
		 * Result.success(orderInfo);
		 */
	}

	/**
	 * orderId：成功 -1：秒杀失败 0： 排队中
	 */
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	@ResponseBody
	public Result<Long> miaoshaResult(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
		model.addAttribute("user", user);
		if (user == null)
			return Result.error(CodeMsg.SESSION_ERROR);
		long result = miaoshaServie.getMiaoshaResult(user.getId(), goodsId);
		return Result.success(result);
	}

	@AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
	@RequestMapping(value = "/path", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> getMiaoshaPath(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
		if (user == null)
			return Result.error(CodeMsg.SESSION_ERROR);
		String path = miaoshaServie.createMiaoshaPath(user, goodsId);
		return Result.success(path);
	}
}
