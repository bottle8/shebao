package com.ice.shebao.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.ibatis.session.SqlSession;

import com.ice.shebao.Const;
import com.ice.shebao.dao.AgeAccountDao;
import com.ice.shebao.dao.NetUtil;
import com.ice.shebao.factory.SqlFactory;
import com.ice.shebao.model.AgeAccount;
import com.ice.shebao.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AgeAccountDaoImpl implements AgeAccountDao {

	/**
	 * 通过个人序号和地区码查询养老账户
	 */
	@Override
	public AgeAccount queryageAccount(AgeAccount ageAccount) {
		SqlSession session = SqlFactory.getSession();
		AgeAccount account = session.selectOne("com.ice.shebao.model.pojo.AgeAccountMapper.queryAgeAccount", ageAccount);
		SqlFactory.closeSessionFactory(session);
		return account;
	}

	/**
	 * 通过身份证和地区码查询养老账户
	 */
	@Override
	public AgeAccount queryAgeAccountByIdentity(AgeAccount ageAccount) {
		SqlSession session = SqlFactory.getSession();
		AgeAccount account = session.selectOne("com.ice.shebao.model.pojo.AgeAccountMapper.queryAgeAccountByidentity", ageAccount);
		SqlFactory.closeSessionFactory(session);
		return account;
	}

	/**
	 * 插入一条养老待遇数据
	 */
	@Override
	public void insertAgeAccount(AgeAccount ageAccount) {
		SqlSession session = SqlFactory.getSession();
		session.insert("com.ice.shebao.model.pojo.AgeAccountMapper.insertAgeAccount", ageAccount);
		session.commit();
		SqlFactory.closeSessionFactory(session);
	}

	/**
	 * 通过mid更新数据
	 */
	@Override
	public void updateAgeAccount(AgeAccount ageAccount) {
		SqlSession session = SqlFactory.getSession();
		session.insert("com.ice.shebao.model.pojo.AgeAccountMapper.insertAgeAccount", ageAccount);
		session.commit();
		SqlFactory.closeSessionFactory(session);
	}

	/**
	 * 获取url路径下的数据
	 * 
	 * @param serial
	 * @param areanum
	 * @return
	 * @throws IOException
	 */
	@Override
	public AgeAccount getHttpData(String serial, String areanum) throws IOException {

		String url = Const.AgeAUrl + "method=queryAgeAccountBySerial&" + "serial=" + serial + "&areanum=" + areanum;
		String convertString = StringUtil.convertString(NetUtil.getInputSream(url));
		JSONObject jsonObject = JSONObject.fromObject(convertString);
		if ("0".equals(jsonObject.get("state"))) {
			AgeAccount ageAccount = new AgeAccount();
			JSONArray array = jsonObject.getJSONArray("databean");

			JSONObject object = (JSONObject) array.opt(0);
			ageAccount.setBuildtime(object.getString("buildtime"));
			ageAccount.setState(object.getString("state"));
			ageAccount.setType(object.getString("type"));
			ageAccount.setUnitCutIn(object.getString("unitCutIn"));
			ageAccount.setAddCutIn(object.getString("addCutIn"));
			ageAccount.setUnitSum(object.getString("unitSum"));
			ageAccount.setPersonAdd(object.getString("personAdd"));
			ageAccount.setPersonInterest(object.getString("personInterest"));
			ageAccount.setPersonSum(object.getString("personSum"));
			ageAccount.setAccountAmont(object.getString("accountAmont"));
			ageAccount.setIdentity(object.getString("identity"));
			ageAccount.setSerial(object.getString("serial"));
			ageAccount.setAreanum(object.getString("areaNum"));
			return ageAccount;
		} else {
			return null;
		}
	}
}
