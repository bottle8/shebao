package com.ice.shebao.service;

import com.ice.shebao.model.JsonDataObject;
import com.ice.shebao.model.User;

public interface UserService {
	
	/**
	 * 通过手机号和身份证号查询用户并比配密码是否正确
	 * @param phoneNum		手机号
	 * @param identity		身份证号
	 * @param password		密码
	 * @return
	 */
	JsonDataObject queryUserByPhoneAndIdentity(String phoneNum,String identity,String password) throws Exception;
	
	/**
	 * 	绑卡
	 * @param identity		身份证
	 * @param shebaocard	社保卡
	 * @param realname		真实姓名
	 * @return
	 * @throws Exception
	 */
	JsonDataObject bandCard(String identity,String shebaocard,String realname) throws Exception;
	
	/**
	 * 注册
	 * @param phone			电话号码
	 * @param password		密码
	 * @param identity		身份证号
	 * @return
	 * @throws Exception
	 */
	JsonDataObject regist(String phone,String password,String identity) throws Exception;
	
	/**
	 * 验证身份证号和姓名是否正确
	 * @param identity		身份证号
	 * @param name			姓名
	 * @return
	 * @throws Exception
	 */
	JsonDataObject verifIdentityAndName(String identity,String name) throws Exception;
	
	/**
	 * 通过电话号码修改密码	
	 * @param phone		电话号码
	 * @param password	密码
	 * @throws Exception
	 */
	void updatePwdByPhone(String phone,String password) throws Exception;
}
