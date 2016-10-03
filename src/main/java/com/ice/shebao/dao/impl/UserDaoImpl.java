package com.ice.shebao.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ice.shebao.dao.UserDao;
import com.ice.shebao.factory.SqlFactory;
import com.ice.shebao.model.User;

public class UserDaoImpl implements UserDao{
	
	public static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	/**
	 * 通过手机号/身份证号 查询用户信息
	 */
	@Override
	public User queryUserByPhone(User user) {
		SqlSession session = SqlFactory.getSession();
		User users = null;
		List<User> userList = new ArrayList<User>();
		
		userList = session.selectList("com.ice.shebao.model.pojo.UserMapper.queryUserByPhone",user);
		logger.debug(userList.size());
		if (userList != null && !userList.isEmpty()) {
			users = userList.get(0);
		}
		SqlFactory.closeSessionFactory(session);
		return users;
	}

	/**
	 * 通过身份证号 和姓名 查询用户信息
	 */
	@Override
	public User queryUserByidentityAndName(User user) {
		SqlSession session = SqlFactory.getSession();
		
		User users = null;
		List<User> userList = new ArrayList<>();
		userList = session.selectList("com.ice.shebao.model.pojo.UserMapper.queryUserByidentityAndName", user);
		logger.debug(userList.size());
		if (userList != null && !userList.isEmpty()) {
			users = userList.get(0);
		}
		SqlFactory.closeSessionFactory(session);
		return users;
	}

	/**
	 * 通过手机号修改密码
	 */
	@Override
	public void updatePwdByPhone(User user) {
		SqlSession session = SqlFactory.getSession();
		session.insert("com.ice.shebao.model.pojo.UserMapper.updatePwdByPhone",user);
		session.commit();
		SqlFactory.closeSessionFactory(session);
	}

}
