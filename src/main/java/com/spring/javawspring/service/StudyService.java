package com.spring.javawspring.service;

import java.util.ArrayList;

import com.spring.javawspring.vo.GuestVO;

public interface StudyService {
	// impl extends 만들기
	
	//처음에 public
	public String[] getCityStringArr(String dodo);

	public ArrayList<String> getCityArrayListArr(String dodo);

	public GuestVO getGuestMid(String mid);

	public ArrayList<GuestVO> getGuestNames(String mid);
	
}
