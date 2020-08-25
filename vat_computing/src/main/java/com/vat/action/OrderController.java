package com.vat.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.vat.bean.AmazonOrder;
import com.vat.bean.User;
import com.vat.service.OrderService;

@RequestMapping(value = "/order")
@RestController
public class OrderController {
    
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
    

    @Resource
    private OrderService orderService;

    @GetMapping(value = "listOrder")
    @Transactional(readOnly = true)
    public Page<AmazonOrder> get(HttpServletRequest request, HttpServletResponse response) throws IOException {
	// @RequestParam Integer page, @RequestParam Integer size;
	String currentPage = (String) request.getParameter("page");
	Integer pageSize = 10;

	User user = (User) request.getSession().getAttribute("user");
	String userId = null;
	if (user != null) {
	    userId = user.getUserId();
	} else {
	    response.sendRedirect(request.getContextPath() + "/index.html");
	    return null;
	}
	
	Page<AmazonOrder> pageInfo = orderService.findOrderByPage(userId, Integer.parseInt(currentPage), pageSize);

	List<AmazonOrder> result = pageInfo.getResult();// 和上面的users结果相同
	if(CollectionUtils.isNotEmpty(result)) {
        	for(AmazonOrder order: result) {
        	   logger.info(order.getOrder_id());
        	}
	}

	return pageInfo;
    }
}
