package com.berke.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	NO_RECORD_EXIST("1002","Kayıt bulunamadı"),
	TOKEN_EXPIRED("1003","Tokenın süresi bitmiştir"),
	USERNAME_NOT_FOUND("1004","Username bulunamadı"),
	GENERAL_EXCEPTION("9999","Genel bir hata oluştu"),
	USERNAME_OR_PASSWORD_INVALID("1005","Kullanıcı adı veya şifre hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1006","Böyle bir refreshToken bulunamadı."),
	REFRESH_TOKEN_EXPIRED("1003","RefreshToken'ın süresi bitmiştir"),
	CURRENCY_RATES_IS_OCCURED("1004","Döviz kuru alınamadı"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1005","Müşterinin bakiyesi yetersizdir"),
	CAR_STATUS_IS_ALREADY_SALED("1006","Bu araba satılmış.");
	private String code;
	private String message;
	
	MessageType(String code,String message) {
		this.code=code;
		this.message=message;
	}
}
