package com.howbuy.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howbuy.trade.service.TradeService;

/**
 * 交易Controller
 * @author LuanYi
 */
@Controller
public class TradeController {

	@Autowired
	private TradeService tradeService;
}
