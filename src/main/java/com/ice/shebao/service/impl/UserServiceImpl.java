package com.ice.shebao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ice.shebao.dao.UserDao;
import com.ice.shebao.factory.DaoFactory;
import com.ice.shebao.factory.SqlFactory;
import com.ice.shebao.model.JsonDataObject;
import com.ice.shebao.model.User;
import com.ice.shebao.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	/**
	 * 通过手机号和身份证号查询用户并比配密码是否正确
	 */
	@Override
	public JsonDataObject queryUserByPhoneAndIdentity(String phoneNum, String identity, String password) throws Exception{
		/**
		 * 1.通过电话号码和身份证去查询数据，
		 * 2.有数据，则进行密码匹配，
		 * 3没数据，则返回失败信息
		 */
		JsonDataObject jsonDataObject = new JsonDataObject();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		UserDao userDao = DaoFactory.getUserDao();
		User userCase = new User();
		userCase.setPhone(phoneNum);
		userCase.setIdentitynum(identity);
		User user = userDao.queryUserByPhone(userCase);
		if (user != null) {
			if (user.getPhone() != null && user.getIdentitynum() != null) {
				if (user.getPassword().equals(password)) {
					Map<String, String> map = getUserMap(user);
					list.add(map);
					jsonDataObject.setDataList(list);
					jsonDataObject.setState("0");
					jsonDataObject.setMessage("登录成功");
				}else{
					jsonDataObject.setMessage("密码错误");
					jsonDataObject.setState("1");
				}
			}else{
				jsonDataObject.setMessage("用户不存在");
				jsonDataObject.setState("2");
			}
		}else{
			jsonDataObject.setMessage("用户不存在");
			jsonDataObject.setState("2");
		}
		
		return jsonDataObject;
	}
	
	
	
	/**
	 * 将User转化成Map
	 * @param user
	 * @return
	 */
	private Map<String, String> getUserMap(User user){
		Map<String, String> map = new HashMap<String,String>();
		map.put("NAME", user.getName());
		map.put("SEX", user.getSex());
		map.put("IDENTITY", user.getIdentitynum());
		map.put("PHONE", user.getPhone());
		map.put("BANK", user.getBank());
		map.put("BUIDLTIME", user.getBuildtime());
		map.put("CARDNUM", user.getCardnum());
		map.put("SERIAL", user.getSerial());
		map.put("AREANUM", user.getAreanum());
		map.put("PHOTO", user.getPhoto());
		
		return map;
	}
	
	
	
	
	
	
	
	
	
//	@Override
//	public User queryByPhone(User user) throws Exception{
//		
//		SqlSession session = DaoFactory.getSession();
//		UserDao userDao = session.getMapper(UserDao.class);
//		List<User> users = userDao.queryUserByPhone(user);
//		if (!users.isEmpty()) {
//			user = users.get(0);
//		}
//		DaoFactory.closeSessionFactory(session);
//		return user;
//	}


}
