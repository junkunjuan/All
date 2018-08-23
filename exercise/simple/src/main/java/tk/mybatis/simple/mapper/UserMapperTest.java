package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {
	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1l);
			Assert.assertNotNull(user);
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAll();
			/*
			 * for(SysUser sysUser : userList)
			 * System.out.println(sysUser.getUserName());
			 */
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRoleByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserId(1l);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}
}
