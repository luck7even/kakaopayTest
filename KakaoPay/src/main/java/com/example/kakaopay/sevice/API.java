package com.example.kakaopay.sevice;

public enum API {
	KAKAOPAY_READY("https://kapi.kakao.com/v1/payment/ready"),
	KAKAOPAY_APPROVE("https://kapi.kakao.com/v1/payment/approve"),
	KAKAOPAY_CANCEL("https://kapi.kakao.com/v1/payment/cancel"),
	KAKAOPAY_ORDER("https://kapi.kakao.com/v1/payment/order"),
	KAKAOPAY_SUBSCRIPTION("https://kapi.kakao.com/v1/payment/subscription");
	
	private String uri;

	API(String uri) {
		this.uri = uri;
	}

	public String getApiUri() {
		return uri;
	}
}
