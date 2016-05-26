package com.howbuy.trade.dao;

import com.howbuy.trade.dto.TradeDTO;

/**
 * 交易dao接口
 * @author LuanYi
 */
public interface TradeMapper {

	public void insertSelective(TradeDTO tradeDTO); 
	
	public void updateByPrimaryKeySelective(TradeDTO tradeDTO); 
}
