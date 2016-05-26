package com.howbuy.trade.service;

import com.howbuy.trade.dto.CustDTO;

/**
 * 用户service接口
 * @author LuanYi
 */
public interface CustService {

	public void addCust(CustDTO custDTO);
	
	public void modifyCust(CustDTO custDTO);
}
