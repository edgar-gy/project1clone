package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.openAPI.WeatherAPI;
import com.example.demo.service.TourService;

@Controller
public class WeatherController {
	@Autowired
	private WeatherAPI api;
	
	@Autowired
	private TourService service;

	@GetMapping("test")
	public String test() {
		return "test";
	};
	//ResponseEntity 화면 그대로 보내는 객체
	//@ResponseBody 보내는 화면그대로?
	@GetMapping("apitest")
	@ResponseBody
	public ResponseEntity<List<Map<String, Object>>> apitest() throws Exception{
		
		int result = 0;
		
		List list = api.weatherapi();
		result = service.insert(list);
		if(result == 1) {
			System.out.println("db에 넣었다!");
		}else {
			System.out.println("실패 돌아가");
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

}
