package com.howbuy.trade.dao;

import com.howbuy.trade.dto.FundDTO;

/**
 * 基金dao接口
 * @author LuanYi
 */
public interface FundMapper {

	public void insertSelective(FundDTO fundDTO); 
	
	public void updateByPrimaryKeySelective(FundDTO fundDTO); 
}
