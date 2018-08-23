package com.meeting.dao;

import com.meeting.model.People;

public interface RegisterDao {
	//注册
	public void register(People people) throws Exception;
	//验证该邮箱是否已注册
	public People verify(String email) throws Exception;
	
	public int userId(People people) throws Exception;
}
