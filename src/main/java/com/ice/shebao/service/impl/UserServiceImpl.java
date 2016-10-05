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
import com.ice.shebao.util.RedisClient;

import net.sf.json.JSONObject;

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


	/**
	 * 绑卡
	 */
	@Override
	public JsonDataObject bandCard(String identity, String shebaocard, String realname) throws Exception {
		/**
		 * 1.从数据库中查询用户
		 * 2.如果有该数据（根据社保卡号比较）返回数据
		 * 3.没有数据，从服务上获取用户信息
		 *   对数据进行比较，如果正确，则将用户信息保存在缓存中
		 */
		JsonDataObject object = new JsonDataObject();
		
		UserDao userDao = DaoFactory.getUserDao();
		User userCase = new User();
		userCase.setIdentitynum(identity);
		userCase.setName(realname);
		User user = userDao.queryUserByidentityAndName(userCase);
		if (user == null) {
			User userInfo = userDao.getHttpData(identity, shebaocard);
			if (userInfo != null) {
				if(userInfo.getCardnum().equals(shebaocard)){
					if (userInfo.getIdentitynum().equals(identity)) {
						if (userInfo.getName().equals(realname)) {
							//将用户信息放入redis缓存中
							RedisClient.set("user"+identity, userInfo);
							object.setState("0");			//绑定成功
							object.setMessage("绑定成功");
						}else{
							object.setState("1");			//姓名不正确
							object.setMessage("姓名号不正确");
						}
					}else{
						object.setState("2");			//身份证号不正确
						object.setMessage("身份证号不正确");
					}
				}else{
					object.setState("3");			//社保卡号不正确
					object.setMessage("社保卡号不正确");
				}
			}else{
				object.setState("3");			//社保卡号不正确
				object.setMessage("社保卡号不正确");
			}
		}else{
			object.setState("4");			//社保卡已经绑定了
			object.setMessage("社保卡号已经绑定");
		}
		return object;
	}

	
	@Override
	public JsonDataObject verifIdentityAndName(String identity, String name) throws Exception {
		/**
		 * 1.从数据库中查询数据
		 * 2.判断数据是否为空，为空身份证号不正确
		 * 3.比较姓名是否正确
		 */
		JsonDataObject json = new JsonDataObject();
		UserDao userDao = DaoFactory.getUserDao();
		User userCase = new User();
		userCase.setIdentitynum(identity);
		User user = userDao.queryUserByidentity(userCase);
		if (user != null) {
			if (name.equals(user.getName())) {
				json.setMessage("验证成功");
				json.setState("0");
			}else{
				json.setMessage("姓名不正确");
				json.setState("1");
			}
		}else{
			json.setMessage("身份证号不正确");
			json.setState("2");
		}
		
		return json;
	}


	/**
	 * 注册
	 */
	@Override
	public JsonDataObject regist(String phone, String password,String identity) throws Exception {
		/**
		 * 1.从缓存中获取用户信息
		 * 2.将电话号码和密码填入用户信息中
		 * 3.写入数据库
		 * 4.删除redis缓存信息
		 */
		JsonDataObject json = new JsonDataObject();
		UserDao userDao = DaoFactory.getUserDao();
		
		String string = RedisClient.get("user"+identity);
		
		JSONObject object = JSONObject.fromObject(string);
		User user = (User) JSONObject.toBean(object, User.class);
		user.setPhone(phone);
		user.setPassword(password);
		
		userDao.insertUser(user);
		
		RedisClient.delete("user"+identity);
		json.setState("0");
		json.setMessage("注册成功");
		return json;
	}


	/**
	 * 通过电话号码修改密码
	 */
	@Override
	public void updatePwdByPhone(String phone, String password) throws Exception {
		/**
		 * 1.修改密码，并返回修改成功信息
		 */
		UserDao userDao = DaoFactory.getUserDao();
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		userDao.updatePwdByPhone(user);
	
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
