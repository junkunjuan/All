package com.meeting.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meeting.dao.LoginDao;
import com.meeting.dao.MemberDao;
import com.meeting.dao.PhotoDao;
import com.meeting.dao.RegisterDao;
import com.meeting.model.People;
import com.meeting.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private RegisterDao registerDao;
	
	@Resource
	private LoginDao loginDao;
	
	@Resource
	private PhotoDao photoDao;
	
	@Resource 
	private MemberDao memberDao;
	
	@Override
	public void register(People people) {
		try {
			registerDao.register(people);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public People verify(String email) {
		People people = new People();
		try {
			people = registerDao.verify(email);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return people;
	}
	
	@Override
	public People login(People people) {
		People people2 = new People();
		try {
			people2 = loginDao.login(people);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return people2;
	}
	
	@Override
	public void upload(People people) {
		try {
			photoDao.updatePic(people);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public String searchPic(People people) {
		String path = null;
		try {
			path = photoDao.searchPic(people);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return path;
	}
	
	@Override
	public void insertPicId(People people) {
		try {
			photoDao.insertPicId(people);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override 
	public int userId(People people) {
		 int id = 0;
		 try {
			 id = registerDao.userId(people);
		 } catch(Exception exception) {
			 exception.printStackTrace();
		 }
		 return id;
	}
	
	@Override
	public void updateMember(People people) {
		try {
			memberDao.updateMember(people);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}
