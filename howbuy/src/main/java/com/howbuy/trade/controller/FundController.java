package com.howbuy.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howbuy.trade.service.FundService;

/**
 * 基金Controller
 * @author LuanYi
 */
@Controller
public class FundController {

	@Autowired
	private FundService fundService;
}
