package com.ice.shebao.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ice.shebao.Const;
import com.ice.shebao.dao.NetUtil;
import com.ice.shebao.dao.UserDao;
import com.ice.shebao.factory.SqlFactory;
import com.ice.shebao.model.User;
import com.ice.shebao.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	 * 通过身份证号 查询用户信息
	 */
	@Override
	public User queryUserByidentity(User user) {
		SqlSession session = SqlFactory.getSession();
		
		User users = null;
		List<User> userList = new ArrayList<>();
		userList = session.selectList("com.ice.shebao.model.pojo.UserMapper.queryUserByidentity", user);
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

	/**
	 * 插入用户信息
	 */
	@Override
	public void insertUser(User user) {
		SqlSession session = SqlFactory.getSession();
		session.insert("com.ice.shebao.model.pojo.UserMapper.insertUser",user);
		session.commit();
		SqlFactory.closeSessionFactory(session);
	}
	
	/**
	 * 通过身份证号和社保卡号从服务端获取用户信息
	 * @throws IOException 
	 */
	@Override
	public User getHttpData(String identity, String shebaoCard) throws IOException {
		String url = Const.UserUrl + "method=requestUserByIdentity&" + "identity=" + identity + "&cardno=" + shebaoCard;
		String convertString = StringUtil.convertString(NetUtil.getInputSream(url));
		JSONObject jsonObject = JSONObject.fromObject(convertString);
		User user = null;
		if ("0".equals(jsonObject.get("state"))) {
			user = new User();
			JSONArray jsonArray = jsonObject.getJSONArray("databean");
			JSONObject object = (JSONObject)jsonArray.opt(0);
			user.setName(object.getString("name"));
			user.setSex(object.getString("sex"));
			user.setIdentitynum(object.getString("identitynum"));
			user.setBank(object.getString("bank"));
			user.setBuildtime(object.getString("buildtime"));
			user.setCardnum(object.getString("cardnum"));
			user.setSerial(object.getString("serial"));
			user.setAreanum(object.getString("areanum"));
			user.setUnitname(object.getString("unitname"));
			user.setPhoto(object.getString("photo"));
		}
		return user;
	}
}
