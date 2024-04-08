package com.example.demo.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("tour")
public class Tour {
	private String stnid;
	private String stnko;
	private String pa;
	private String ps;
	private String avgtamax;
	private String avgtamin;
	private String taavg;
	private String tamin;
	private String avgtgmin;
}
