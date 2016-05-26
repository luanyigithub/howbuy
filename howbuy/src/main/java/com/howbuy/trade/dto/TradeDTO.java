package com.howbuy.trade.dto;

/**
 * 好买交易DTO
 * @author LuanYi
 */
public class TradeDTO {

	//交易id
	private Integer tradeId;
	
	//与custId字段关联
	private Integer tradeCustId;
	
	//基金代码
	private String fundCode;
	
	//购买金额
	private String amount;
	
	//委托号
	private String contractNo;
	
	//基金简称
	private String fundAbbr;
	
	//交易类型
	private String busiType;
	
	//交易状态
	private String tradeStatus;
	
	//基金份额
	private String tradeVol;
	
	//交易金额
	private String tradeAmt;
	
	//手续费
	private String fee;
	
	//银行卡号
	private String bankAcct;
	
	//申请日期
	private String appDt;
	
	//银行编号
	private String bankCode;
	
	//净值
	private String nav;
	
	//申请时间
	private String appTm;
	
	//分红方式
	private String divMode;
	
	//确认份额
	private String ackVol;
	
	//确认金额
	private String ackAmt;
	
	//付款状态
	private String txPmtFlag;
	
	//撤单标志
	private String cancelFlag;
	
	//确认日期
	private String ackDt;

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getTradeCustId() {
		return tradeCustId;
	}

	public void setTradeCustId(Integer tradeCustId) {
		this.tradeCustId = tradeCustId;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getFundAbbr() {
		return fundAbbr;
	}

	public void setFundAbbr(String fundAbbr) {
		this.fundAbbr = fundAbbr;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeVol() {
		return tradeVol;
	}

	public void setTradeVol(String tradeVol) {
		this.tradeVol = tradeVol;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	public String getAppDt() {
		return appDt;
	}

	public void setAppDt(String appDt) {
		this.appDt = appDt;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getAppTm() {
		return appTm;
	}

	public void setAppTm(String appTm) {
		this.appTm = appTm;
	}

	public String getDivMode() {
		return divMode;
	}

	public void setDivMode(String divMode) {
		this.divMode = divMode;
	}

	public String getAckVol() {
		return ackVol;
	}

	public void setAckVol(String ackVol) {
		this.ackVol = ackVol;
	}

	public String getAckAmt() {
		return ackAmt;
	}

	public void setAckAmt(String ackAmt) {
		this.ackAmt = ackAmt;
	}

	public String getTxPmtFlag() {
		return txPmtFlag;
	}

	public void setTxPmtFlag(String txPmtFlag) {
		this.txPmtFlag = txPmtFlag;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getAckDt() {
		return ackDt;
	}

	public void setAckDt(String ackDt) {
		this.ackDt = ackDt;
	}
}
