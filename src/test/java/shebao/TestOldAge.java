package shebao;

import java.io.IOException;

import org.junit.Test;

import com.ice.shebao.dao.AgeAccountDao;
import com.ice.shebao.dao.NetUtil;
import com.ice.shebao.factory.DaoFactory;
import com.ice.shebao.model.AgeAccount;

public class TestOldAge {
	
	/**
	 * 测试养老账户DAO
	 */
	@Test
	public void testAgeAccount(){
		AgeAccountDao dao = DaoFactory.getAgeAccountDao();
		AgeAccount account = new AgeAccount();
		account.setSerial("87632878");
		account.setAreanum("420600");
		AgeAccount ageAccount = dao.queryageAccount(account);
		if (ageAccount != null) {
			System.out.println(ageAccount.getAid());
		}else{
			System.out.println("查无数据");
		}
	}
	
	@Test
	public void testGetHttp(){
		AgeAccountDao dao = DaoFactory.getAgeAccountDao();
		try {
			AgeAccount httpData = dao.getHttpData("87632878", "420600");
			if (httpData != null) {
				System.out.println(httpData.getAddCutIn());
			}else{
				System.out.println("查无数据");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
