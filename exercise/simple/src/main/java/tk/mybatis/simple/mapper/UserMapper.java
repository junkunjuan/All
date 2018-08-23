package tk.mybatis.simple.mapper;

import java.util.List;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public interface UserMapper {
	
	/**
	 * 通过id查询用户
	 * 
	 * @param id
	 * @return 
	 */
	SysUser selectById(Long id);
	
	/**
	 * 查询全部用户
	 * @return
	 */
	List<SysUser> selectAll();
	
	/**
	 * 根据用户id获取角色信息
	 * 
	 * @param
	 * @return
	 */
	List<SysRole> selectRolesByUserId(Long userId);
	
	int insert2(SysUser sysUser);
	
	int insert3(SysUser sysUser);
}
