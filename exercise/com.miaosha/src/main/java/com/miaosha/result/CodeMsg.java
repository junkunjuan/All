package com.miaosha.result;

public class CodeMsg {
	private int code;
	private String msg;

	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
	public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问太频繁！");
	// 服务端异常
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

	// 通用错误码
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

	// 登录模块5002xx
	public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
	public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "用户不存在");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");

	// 订单模块 5004XX
	public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");

	// 秒杀模块5005xx
	public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
	public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");

	private CodeMsg(int i, String string) {
		code = i;
		msg = string;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CodeMsg fillArgs(Object... objects) {
		int code = this.code;
		String message = String.format(this.msg, objects);
		return new CodeMsg(code, message);
	}
}
