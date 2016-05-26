package com.howbuy.trade.service;

import com.howbuy.trade.dto.FundDTO;

/**
 * 交易service接口
 * @author LuanYi
 */
public interface FundService {

	public void addFund(FundDTO fundDTO);
	
	public void modifyFund(FundDTO fundDTO);
}
