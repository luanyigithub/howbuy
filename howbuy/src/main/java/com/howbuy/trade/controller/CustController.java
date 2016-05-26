package com.howbuy.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howbuy.trade.service.CustService;

/**
 * 用户Controller
 * @author LuanYi
 */
@Controller
public class CustController {

	@Autowired
	private CustService custService;
}
