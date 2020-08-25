package com.vat.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.vat.service.UserService;

@RestController
public class UserRestService {
    
    private final static Logger logger = LoggerFactory.getLogger(UserRestService.class);

    @Resource
    private UserService userService;
    
    
}
