package com.howbuy.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howbuy.trade.dao.CustMapper;
import com.howbuy.trade.dto.CustDTO;
import com.howbuy.trade.service.CustService;

/**
 * 用户Service接口实现
 * @author LuanYi
 */
@Service("custService")
public class CustServiceImpl implements CustService {

	@Autowired
	private CustMapper custMapper;
	
	public void addCust(CustDTO custDTO) {
		custMapper.insertSelective(custDTO);
	}

	public void modifyCust(CustDTO custDTO) {
		custMapper.updateByPrimaryKeySelective(custDTO);
	}
}
