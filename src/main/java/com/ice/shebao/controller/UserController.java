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
	 * 注册第一步  绑卡
	 * @param identity		身份证		
	 * @param shebaocard	社保卡
	 * @param realName		真实姓名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/bandcard/{identity}/{shebaocard}/{realname}",method=RequestMethod.POST)
	public JsonDataObject bandCard(@PathVariable(value="identity")String identity,
			@PathVariable(value="shebaocard")String shebaocard,
			@PathVariable(value="realname")String realName) {
		JsonDataObject json = null;
		try {
			json = service.bandCard(identity, shebaocard, realName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return json;
	}
	
	/**
	 * 用户注册
	 * @param phone			电话号码
	 * @param password		密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/regist/{phone}/{password}/{identity}",method=RequestMethod.POST)
	public JsonDataObject regist(@PathVariable(value="phone")String phone,
			@PathVariable(value="password")String password,
			@PathVariable(value="identity")String identity){
		JsonDataObject json = null;
		
		try {
			json = service.regist(phone, password, identity);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		return json;
	}

	
	/**
	 * 身份证和密码验证用户
	 * @param identity
	 * @param name
	 */
	@ResponseBody
	@RequestMapping(value="/verifyIdentity/{identity}/{name}",method=RequestMethod.POST)
	public JsonDataObject verifIdentity(@PathVariable(value="identity") String identity,
			@PathVariable(value="name") String name){
		JsonDataObject json = null;
		try {
			json = service.verifIdentityAndName(identity, name);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return json;
	}
	
	/**
	 * 通过手机号修改密码
	 * @param phone
	 * @param password
	 */
	@ResponseBody
	@RequestMapping(value="/updatePwdByPhone/{phone}/{password}" , method=RequestMethod.POST)
	public JsonDataObject updatePwdByPhone(@PathVariable(value="phone")String phone,
			@PathVariable(value="password") String password){
		JsonDataObject json = new JsonDataObject();
		try {
			service.updatePwdByPhone(phone,password);
			json.setMessage("修改成功");
			json.setState("0");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json.setMessage("修改失败");
			json.setState("1");
		}
		return json;
	}
	
}
