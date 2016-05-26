package com.howbuy.trade.service;

import com.howbuy.trade.dto.TradeDTO;

/**
 * 交易service接口
 * @author LuanYi
 */
public interface TradeService {

	public void addTrade(TradeDTO tradeDTO);
	
	public void modifyTrade(TradeDTO tsradeDTO);
}
