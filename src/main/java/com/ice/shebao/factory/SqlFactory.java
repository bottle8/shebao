package com.ice.shebao.factory;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlFactory {
private static String MYBATIS_COFING = "mybatis-config.xml";
	
	private static SqlSessionFactory sessionFactory ;
	
	private static Reader reader;
	
	/**
	 * 获取SqlSessionFactory
	 */
	static{
		try {
			reader = Resources.getResourceAsReader(MYBATIS_COFING);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取session
	 * @return
	 */
	public static SqlSession getSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * 关闭session
	 * @param session
	 */
	public static void closeSessionFactory(SqlSession session){
		if (session != null) {
			session.close();
		}
	}
}
