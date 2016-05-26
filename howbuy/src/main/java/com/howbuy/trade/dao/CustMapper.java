package com.howbuy.trade.dao;

import com.howbuy.trade.dto.CustDTO;

/**
 * 用户dao接口
 * @author LuanYi
 */
public interface CustMapper {

	public void insertSelective(CustDTO custDTO); 
	
	public void updateByPrimaryKeySelective(CustDTO custDTO); 
}
