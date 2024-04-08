package com.example.demo.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("tour")
public class Tour {
	private String trrsrtNm;
	private String rdnmadr;
	private String lnmadr;
	private String latitude;
	private String longitude;
	private String trrsrtIntrcn;
	private String prkplceCo;
	private String phoneNumber;
	
}
