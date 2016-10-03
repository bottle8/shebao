package com.ice.shebao.factory;

import com.ice.shebao.dao.AgeAccountDao;
import com.ice.shebao.dao.UserDao;
import com.ice.shebao.dao.impl.AgeAccountDaoImpl;
import com.ice.shebao.dao.impl.UserDaoImpl;

public class DaoFactory {
	
	/**
	 * 获取UserDao的接口实现实例化
	 * @return
	 */
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
	
	/**
	 * 获取AgeAccountDao的接口实现实例化
	 * @return
	 */
	public static AgeAccountDao getAgeAccountDao() {
		return new AgeAccountDaoImpl();
	}
}
