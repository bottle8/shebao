package com.ice.shebao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ice.shebao.factory.ServiceFactory;
import com.ice.shebao.model.Json;
import com.ice.shebao.model.JsonDataObject;
import com.ice.shebao.model.User;
import com.ice.shebao.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	private UserService service = ServiceFactory.getUserService();

	/**
	 * 用户登录
	 * @param phone		电话号码
	 * @param identity  身份证号码
	 * @param password	密码
	 */
	@ResponseBody
	@RequestMapping(value="/login/{phone}/{identity}/{password}",method=RequestMethod.POST)
	public JsonDataObject login(@PathVariable(value="phone")String phone,
			@PathVariable(value="identity")String identity,
			@PathVariable(value="password")String password){
		logger.info("Start>>>>>>>>>>>  login");
		
		JsonDataObject json = null;
		try {
			json = service.queryUserByPhoneAndIdentity(phone, identity, password);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("End>>>>>>>>>>>  login");
		return json;
	}

	
	/**
	 * 身份证和密码验证用户
	 * @param identity
	 * @param name
	 */
	@RequestMapping(value="/verifyIdentity",method=RequestMethod.POST)
	public void verifIdentity(@RequestParam(value="identity") String identity,
			@RequestParam(value="name") String name){
		User userCase = new User();
		userCase.setIdentitynum(identity);
		userCase.setName(name);
		try {
//			User user = userservice.queryUserByidentityAndName(userCase);
//			if (user != null) {
//				//用户存在
//			}else{
//				//用户不存在
//			}
		} catch (Exception e) {
			//服务器异常
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过手机号修改密码
	 * @param phone
	 * @param password
	 */
	@RequestMapping(value="updatePwdByPhone" , method=RequestMethod.POST)
	public void updatePwdByPhone(@RequestParam(value="phone")String phone,
			@RequestParam(value="password") String password){
		User userCase = new User();
		userCase.setPhone(phone);
		userCase.setPassword(password);
		
		try {
//			userservice.updatePwdByPhone(userCase);
			//修改成功
			
		} catch (Exception e) {
			//修改密码失败
			e.printStackTrace();
		}
	}
	
}
