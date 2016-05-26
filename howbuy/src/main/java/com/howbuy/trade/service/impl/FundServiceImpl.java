package com.howbuy.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howbuy.trade.dao.FundMapper;
import com.howbuy.trade.dto.FundDTO;
import com.howbuy.trade.service.FundService;

/**
 * 基金Service接口实现
 * @author LuanYi
 */
@Service("fundService")
public class FundServiceImpl implements FundService {

	@Autowired
	private FundMapper fundMapper;
	
	public void addFund(FundDTO fundDTO) {
		fundMapper.insertSelective(fundDTO);
	}

	public void modifyFund(FundDTO fundDTO) {
		fundMapper.updateByPrimaryKeySelective(fundDTO);
	}
}
