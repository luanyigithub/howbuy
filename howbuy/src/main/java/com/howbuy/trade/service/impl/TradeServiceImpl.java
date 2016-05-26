package com.howbuy.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howbuy.trade.dao.TradeMapper;
import com.howbuy.trade.dto.TradeDTO;
import com.howbuy.trade.service.TradeService;

/**
 * 交易Service接口实现
 * @author LuanYi
 */
@Service("tradeService")
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeMapper tradeMapper;
	
	public void addTrade(TradeDTO tradeDTO) {
		tradeMapper.insertSelective(tradeDTO);
	}

	public void modifyTrade(TradeDTO tradeDTO) {
		tradeMapper.updateByPrimaryKeySelective(tradeDTO);
	}
}
