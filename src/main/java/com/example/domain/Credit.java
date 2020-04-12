package com.example.domain;

/**
 * アプリケーションから来たカード情報を受け取る.
 * 
 * @author oyamadakenji
 *
 */
public class Credit {

	/** 注文番号(Orderのid) */
	private String order_number;
	/** セキュリティコード */
	private String card_cvv;
	/** カード有効期限(年) */
	private String card_exp_year;
	/** カード有効期限(月) */
	private String card_exp_month;
	
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getCard_cvv() {
		return card_cvv;
	}
	public void setCard_cvv(String card_cvv) {
		this.card_cvv = card_cvv;
	}
	public String getCard_exp_year() {
		return card_exp_year;
	}
	public void setCard_exp_year(String card_exp_year) {
		this.card_exp_year = card_exp_year;
	}
	public String getCard_exp_month() {
		return card_exp_month;
	}
	public void setCard_exp_month(String card_exp_month) {
		this.card_exp_month = card_exp_month;
	}
	@Override
	public String toString() {
		return "Credit [order_number=" + order_number + ", card_cvv=" + card_cvv + ", card_exp_year=" + card_exp_year
				+ ", card_exp_month=" + card_exp_month + "]";
	}
}
