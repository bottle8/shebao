package com.ice.shebao.factory;

import com.ice.shebao.service.AgeAccountService;
import com.ice.shebao.service.AgeAccountServiceImpl;
import com.ice.shebao.service.UserService;
import com.ice.shebao.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	/**
	 * 获取UserService的接口实现实例化
	 * @return
	 */
	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	
	/**
	 * 获取AgeAccountService的接口实现实例化
	 * @return
	 */
	public static AgeAccountService getAgeAccountService() {
		return new AgeAccountServiceImpl();
	}
}
