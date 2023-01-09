 package com.spring.javawspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.GuestDAO;
import com.spring.javawspring.vo.GuestVO;

//꼭하장진경아!>0<
@Service
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	GuestDAO guestDAO; //(3) guest DAO package만들고 guestDAO interface만들기


	@Override
	public void setGuestInput(GuestVO vo) {
		guestDAO.setGuestInput(vo);
	}

	@Override
	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize) {
		return guestDAO.getGuestList(startIndexNo,pageSize);
	}

	@Override
	public int totRecCnt() {
		return guestDAO.totRecCnt();
	}

	@Override
	public void guestDeleteGet(int idx) {
		guestDAO.guestDeleteGet(idx);
	}
}
