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
	
}
