package com.spring.javawspring.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.GuestVO;

public interface GuestDAO {
//(4) guestMapper.xml 만들기
	
	//처음에 public 꼭 써라

	public void setGuestInput(@Param("vo") GuestVO vo);

	public ArrayList<GuestVO> getGuestList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int totRecCnt();

	public void guestDeleteGet(@Param("idx") int idx);
	
}
