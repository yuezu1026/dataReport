/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vat.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vat.bean.User;
import com.vat.service.ConfigService;

@Controller
public class ConfigController {

    private final static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Resource
    private ConfigService configService;

    @RequestMapping(value = "/computingConfig.do", method = RequestMethod.POST)
    @ResponseBody
    public String computingConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String computingMethod = request.getParameter("computingMethod");
	
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	
	String userId = user.getUserId();
	
	configService.saveComputingConfig(userId, computingMethod);

	Map<String, String> map = new HashMap<>();
	map.put("success", "success");
	map.put("computingMethod", computingMethod);
	ObjectMapper mapper = new ObjectMapper();
	String resultString = null;
	try {
	    resultString = mapper.writeValueAsString(map);
	} catch (JsonProcessingException e) {
	    logger.error("error", e);
	}
	return resultString;
    }

}