package com.meeting.dao;

import org.springframework.stereotype.Repository;

import com.meeting.model.People;

@Repository
public interface PhotoDao {
	
	public void updatePic(People people) throws Exception;
	
	public String searchPic(People people) throws Exception;
	
	public void insertPicId(People people) throws Exception;
}
