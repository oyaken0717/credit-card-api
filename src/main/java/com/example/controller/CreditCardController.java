package com.example.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Credit;

//■ http://niwaka.hateblo.jp/entry/2015/03/31/215844
//■	https://fresopiya.com/2019/09/16/webapijson/
@Controller
@EnableAutoConfiguration
@RequestMapping("/credit-card")
public class CreditCardController {

//	@RequestMapping("")
//	public String start() {
////		return "start1";
//		return "start2";
//	}
//
////	@RequestMapping("/to-cancel")
//	@RequestMapping("/to-payment")
//	@ResponseBody
////	public Map<String, String> toCancel(Credit credit) {
//	public Map<String, String> toPayment(Credit credit) {
////		return cancel(credit);
//		return payment(credit);
//	}

//	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> payment(@RequestBody Credit credit) {
		boolean flag = true;
		Map<String, String> map = new HashMap<>();

		// ■ E-02:セキュリティコードが「123」かどうか
		if (!"123".equals(credit.getCard_cvv())) {
			flag = false;
			map.put("status", "error");
			map.put("message", "セキュリティコードが123ではありません。");
			map.put("error_code", "E-02");
		}

		// ■ E-03:カードの有効期限に数値以外の値が渡された場合
		try {
			String year = credit.getCard_exp_year();
			String month = credit.getCard_exp_month();
				// ■ E-01:カードの有効期限が実行時年月よりも「前」だった場合
				Integer cardYear = Integer.parseInt(year);
				Integer cardMonth = Integer.parseInt(month);
				LocalDate ima = LocalDate.now();
				LocalDate deadline = LocalDate.of(cardYear, cardMonth, 1);
				if (deadline.isBefore(ima)) {
					flag = false;
					map.put("status", "error");
					map.put("message", "カードの有効期限が切れています。");
					map.put("error_code", "E-01");				
				}
		} catch (Exception e) {
			flag = false;
			map.put("status", "error");
			map.put("message", "カードの有効期限に数値以外の値か含まれています。");
			map.put("error_code", "E-03");
		}

		// ■ E-00:正しい情報が送られてきた場合
		if (flag) {
			map.put("status", "success");
			map.put("message", "OK.");
			map.put("error_code", "E-00");
		}
		return map;
	}

	// ■ 自分のブラウザで確認できるver:GET
//	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> cancel(@RequestBody Credit credit) {
		// ■ @RequestBody:リクエストを取得し、指定された型に値を注入
		Map<String, String> map = new HashMap<>();
		try {
			Integer.parseInt(credit.getOrder_number());
			map.put("status", "success");
			map.put("message", "canceld.");
			map.put("error_code", "E-00");
		} catch (Exception e) {
			map.put("status", "error");
			map.put("message", "何らかの理由で数値以外の値が含まれています。");
			map.put("error_code", "E-03");
		}
		// ■ 出力結果 {"error_code":"E-00","message":"canceld.","status":"success"}
		return map;
	}
}
