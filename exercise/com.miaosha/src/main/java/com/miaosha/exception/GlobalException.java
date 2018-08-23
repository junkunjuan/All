package com.miaosha.exception;

import com.miaosha.result.CodeMsg;

public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private CodeMsg cMsg;

	public GlobalException(CodeMsg cMsg) {
		super(cMsg.toString());
		this.cMsg = cMsg;
	}

	public CodeMsg getCm() {
		return cMsg;
	}
}
