package com.meeting.dao;

import org.springframework.stereotype.Repository;

import com.meeting.model.People;

@Repository
public interface MemberDao {
	
	//个人资料
	public void updateMember(People people) throws Exception;
}
