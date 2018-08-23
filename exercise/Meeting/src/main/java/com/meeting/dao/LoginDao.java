package com.meeting.dao;

import org.springframework.stereotype.Repository;

import com.meeting.model.People;

@Repository
public interface LoginDao {
	//登录
	public People login(People people) throws Exception;
}
