package com.spring.javawspring.service;

import java.util.ArrayList;

import com.spring.javawspring.vo.GuestVO;

// (2) Interface GuestService 만든 후 GuestServiceImpl class만들기 (interface guestService implement 해야 함)
public interface GuestService {
	//처음에 퍼블릭 꽅 붙이기 

	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize);

	public void setGuestInput(GuestVO vo);

	public int totRecCnt();

	public void guestDeleteGet(int idx);

}
