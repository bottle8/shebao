package com.ice.shebao.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ice.shebao.dao.AgeAccountDao;
import com.ice.shebao.factory.DaoFactory;
import com.ice.shebao.model.AgeAccount;
import com.ice.shebao.model.JsonDataObject;
import com.ice.shebao.model.User;

public class AgeAccountServiceImpl implements AgeAccountService {

	private Logger logger = Logger.getLogger(AgeAccountServiceImpl.class);
	private AgeAccountDao dao = DaoFactory.getAgeAccountDao();
	
	/**
	 * 通过serial和areanum查询养老账户
	 */
	@Override
	public JsonDataObject queryAgeAccount(String serial, String areanum) throws IOException {
		/**
		 * 1.查询数据库中是否有数据
		 * 2.有数据，直接返回
		 * 3.没有数据，网络访问获取数据
		 * 	插入到数据库
		 *  返回数据
		 */
		JsonDataObject jsonDataObject = new JsonDataObject();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		AgeAccount ageAccount = new AgeAccount();
		ageAccount.setSerial(serial);
		ageAccount.setAreanum(areanum);
		AgeAccount account = dao.queryageAccount(ageAccount);
		if (account == null) {
			//网络请求数据
			logger.info("请求网络");
			account = dao.getHttpData(serial, areanum);
			if (account != null) {
				//将数据插入到数据库中
				logger.info("插入数据到数据库中");
				dao.insertAgeAccount(account);
				//返回数据
				list.add(convertMap(account));
				jsonDataObject.setDataList(list);
				jsonDataObject.setMessage("查询成功");
				jsonDataObject.setState("0");
				logger.info(jsonDataObject.toString());
			}else{
				jsonDataObject.setMessage("查无数据");
				jsonDataObject.setState("1");
			}
			
		}else{
			logger.info("数据库中有数据");
			list.add(convertMap(account));
			jsonDataObject.setDataList(list);
			jsonDataObject.setMessage("查询成功");
			jsonDataObject.setState("0");
			logger.info(jsonDataObject.toString());
		}
		
		return jsonDataObject;
	}

	
	/**
	 * 将AgeAccount转化成Map
	 * @param ageAccount
	 * @return
	 */
	private Map<String, String> convertMap(AgeAccount ageAccount){
		Map<String, String> map = new HashMap<String,String>();
		map.put("buildtime", ageAccount.getBuildtime());
		map.put("type", ageAccount.getType());
		map.put("state", ageAccount.getState());
		map.put("unitCutIn", ageAccount.getUnitCutIn());
		map.put("addCutIn", ageAccount.getAddCutIn());
		map.put("unitSum", ageAccount.getUnitSum());
		map.put("personAdd", ageAccount.getPersonAdd());
		map.put("personInterest", ageAccount.getPersonInterest());
		map.put("personSum", ageAccount.getPersonSum());
		map.put("accountAmont", ageAccount.getAccountAmont());
		
		return map;
	}
}
