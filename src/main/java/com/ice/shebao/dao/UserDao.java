package com.ice.shebao.dao;

import java.io.IOException;
import java.util.List;

import com.ice.shebao.model.User;

public interface UserDao {
	/**
	 * 通过电话号码/身份证号码查询用户信息
	 * 
	 * @return
	 */
	public User queryUserByPhone(User user);

	/**
	 * 通过身份证号码和姓名查询用户信息
	 * 
	 * @param user
	 * @return
	 */
	public User queryUserByidentityAndName(User user);

	/**
	 * 通过身份证号码查询用户信息
	 * 
	 * @param user
	 * @return
	 */
	public User queryUserByidentity(User user);
	
	/**
	 * 通过电话号码修改密码
	 * @param user
	 */
	public void updatePwdByPhone(User user);
	
	/**
	 * 插入用户信息
	 * @param user
	 */
	public void insertUser(User user);
	
	/**
	 * 通过身份证号和社保卡号从服务端获取用户信息
	 * @param identity			身份证号
	 * @param shebaoCard		社保卡号
	 * @return
	 */
	public User getHttpData(String identity,String shebaoCard)throws IOException;
	
}
