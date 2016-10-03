package shebao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ice.shebao.dao.UserDao;
import com.ice.shebao.factory.DaoFactory;
import com.ice.shebao.model.User;
import com.ice.shebao.service.UserService;
import com.ice.shebao.service.impl.UserServiceImpl;

public class TestMybatis {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		UserDao userDao = DaoFactory.getUserDao();
		User userTest = new User();
		userTest.setPhone("13476257867");
		userTest.setPassword("123456");
		userTest.setIdentitynum("null");
		User user = null;
		try {
			user = userDao.queryUserByPhone(userTest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			System.out.println(user.getPhone());
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void test1() {
		int m1= 8;
		int n1 =4;
		int m = 2;
		
		System.out.printf("%d 和 %d 的最大公约数 = %d，最小公倍数 = %d ",m1,n1,m,m1*n1/m);

	}
	
}
