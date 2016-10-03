package com.ice.shebao.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ice.shebao.factory.ServiceFactory;
import com.ice.shebao.model.JsonDataObject;
import com.ice.shebao.service.AgeAccountService;

@Controller
@RequestMapping("/oldage")
public class OldAgeController {
	
	private static Logger logger = Logger.getLogger(OldAgeController.class);

	/**
	 * 通过serial和areanum查询养老账户
	 * @param serial
	 * @param areanum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ageAccount/{serial}/{areanum}",method=RequestMethod.POST)
	public JsonDataObject queryAgeAccount(@PathVariable(value="serial")String serial,
			@PathVariable(value="areanum")String areanum) {
		logger.info("start>>>>>>>>>>>>>>>>>> queryAgeAccount");
		JsonDataObject object = new JsonDataObject();
		AgeAccountService service = ServiceFactory.getAgeAccountService();
		try {
			object = service.queryAgeAccount(serial, areanum);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("end>>>>>>>>>>>>>>>>>> queryAgeAccount");
		return object;
	}
}
