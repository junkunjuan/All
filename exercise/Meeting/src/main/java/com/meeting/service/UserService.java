package com.meeting.service;

import com.meeting.model.People;

public interface UserService {
	
	//注册
	public void register(People people);
	
	//验证邮箱是否已注册
	public People verify(String email);
	
	//登录
	public People login(People people);
	
	//上传图片
	public void upload(People people);
	
	//查找图片路径
	public String searchPic(People people);
	
	public void insertPicId(People people);
	
	public int userId(People people);
	
	//个人资料
	public void updateMember(People people);
}
