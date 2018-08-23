package com.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaosha.dao.UserDao;
import com.miaosha.domain.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userdao;
	
	public User getById(int id) {
		return userdao.getById(id);
	}
}
