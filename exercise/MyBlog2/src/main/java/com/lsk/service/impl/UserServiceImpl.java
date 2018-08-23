package com.lsk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lsk.dao.UserDao;
import com.lsk.model.User;
import com.lsk.model.dto.UserDto;
import com.lsk.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource 
	private UserDao userDao;
	
	@Override
	public UserDto login(User user) {
		UserDto userDto = null;
		try  {
			userDto = userDao.login(user);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return userDto;
	}
}
