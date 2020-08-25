package com.vat.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class BaseErrorPageController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(BaseErrorPageController.class);

    @Override
    public String getErrorPath() {
	logger.info("进入自定义错误页面");
	return "error/error";
    }

    @RequestMapping
    public String error() {
	return getErrorPath();
    }
}