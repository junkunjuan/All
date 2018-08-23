package com.lsk.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lsk.model.User;
import com.lsk.model.dto.UserDto;

@Repository
public interface UserDao {
	// 用户登录
	public UserDto login(User user) throws Exception;
	
	/*// 添加或保存用户
    public void save(User user) throws Exception;

    // 更新用户
    public void update(User user) throws Exception;

    // 获取用户列表
    public List<User> getUsers() throws Exception;

    // 删除用户
    public void delete(Integer id) throws Exception;

    // 获取用户
    public User getUser(Integer id) throws Exception;

    // 分页获取用户
    public List<User> pagenation(Pager pager) throws Exception;

    // 是否存在用户
    public int userIsNotEmpty(String name) throws Exception;*/
}
