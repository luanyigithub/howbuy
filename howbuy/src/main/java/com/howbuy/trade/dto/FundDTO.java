package com.howbuy.trade.dto;

/**
 * 好买基金DTO
 * @author LuanYi
 */
public class FundDTO {

	//基金Id
	private Integer fundId;
	
	//搜索关键字
	private String searchKey;
	
	//第几页
	private String pageNo;
	
	//每页数量
	private String pageSize;
	
	//响应代码
	private String returnCode;
	
	//响应描述
	private String returnDesc;
	
	//基金列表
	private String fundList;

	public Integer getFundId() {
		return fundId;
	}

	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnDesc() {
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public String getFundList() {
		return fundList;
	}

	public void setFundList(String fundList) {
		this.fundList = fundList;
	}
}
