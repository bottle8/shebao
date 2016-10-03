package com.ice.shebao.dao;

import java.io.IOException;

import com.ice.shebao.model.AgeAccount;

public interface AgeAccountDao {
	/**
	 * 通过个人序号和地区码查询养老账户
	 * @param ageAccount
	 * @return
	 */
	AgeAccount queryageAccount(AgeAccount ageAccount);
	
	/**
	 * 通过身份证和地区码查询养老账户
	 * @param ageAccount
	 * @return
	 */
	AgeAccount queryAgeAccountByIdentity(AgeAccount ageAccount);
	
	
	/**
	 * 插入一条养老待遇数据
	 * @param ageAccount
	 */
	void insertAgeAccount(AgeAccount ageAccount);
	
	/**
	 * 通过aid更新养老待遇
	 * @param ageAccount
	 * @return				
	 */
	void updateAgeAccount(AgeAccount ageAccount);
	
	/**
	 * 网络请求数据
	 * @param serial
	 * @param areanum
	 * @return
	 * @throws IOException
	 */
	public AgeAccount getHttpData(String serial, String areanum) throws IOException;
}
