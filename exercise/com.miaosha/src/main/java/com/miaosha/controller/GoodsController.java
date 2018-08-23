package com.miaosha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.domain.MiaoshaUser;
import com.miaosha.result.Result;
import com.miaosha.service.GoodsService;
import com.miaosha.service.MiaoshaUserService;
import com.miaosha.vo.GoodsDetailVo;
import com.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	MiaoshaUserService miaoshaUserService;

	@Autowired
	GoodsService goodsService;

	@RequestMapping("/to_list")
	public String list(Model model, MiaoshaUser user) {
		model.addAttribute("user", user);
		// 查询商品列表
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList", goodsList);
		return "goods_list";
	}

	@RequestMapping("/detail/{goodsId}")
	@ResponseBody
	public Result<GoodsDetailVo> detail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId) {
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		long startAt = goods.getStartDate().getTime();
		long endAt = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();
		int miaoshaStatus = 0;
		int remainSeconds = 0;
		if (now < startAt) {
			// 秒杀还没开始，倒计时
			miaoshaStatus = 0;
			remainSeconds = (int) ((startAt - now) / 1000);
		} else if (now > endAt) {
			// 秒杀已经结束
			miaoshaStatus = 2;
			remainSeconds = -1;
		} else {
			// 秒杀进行中
			miaoshaStatus = 1;
			remainSeconds = 0;
		}
		GoodsDetailVo vo = new GoodsDetailVo();
		vo.setMiaoshaStatus(miaoshaStatus);
		vo.setRemainSeconds(remainSeconds);
		vo.setGoods(goods);
		vo.setUser(user);
		return Result.success(vo);
	}
}
