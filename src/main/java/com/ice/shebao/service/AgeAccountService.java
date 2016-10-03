package com.ice.shebao.service;

import java.io.IOException;

import com.ice.shebao.model.JsonDataObject;

public interface AgeAccountService {
	JsonDataObject queryAgeAccount(String serial,String areanum)throws IOException;
}
