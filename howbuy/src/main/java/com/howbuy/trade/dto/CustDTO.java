package com.howbuy.trade.dto;

/**
 * 好买用户DTO
 * @author LuanYi
 */
public class CustDTO {
	
	//用户id
	private Integer custId;
	
	//手机号码
	private String mobile;
	
	//客户姓名
	private String custName;
	
	//证件号码
	private String idNo;
	
	//用户密码
	private String custPass;
	
	//开户状态
	private String status;

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getCustPass() {
		return custPass;
	}

	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
