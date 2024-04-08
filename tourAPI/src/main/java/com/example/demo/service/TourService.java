package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.TourDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourService {
	
	@Autowired
	private final TourDAO dao;
	
	public int insert(List list) {
		return dao.insert(list);
	}
}
