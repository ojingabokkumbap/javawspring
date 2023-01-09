package com.spring.javawspring.service;

import java.util.ArrayList;

import com.spring.javawspring.vo.MemberVO;

public interface MemberService {

	public MemberVO getMemberIdCheck(String mid);

	public MemberVO getMemberNickCheck(String nickName);

	public int setMemberJoinOk(MemberVO vo);

	public void setMemberVisitProcess(MemberVO vo);

	public int totRecCnt();

	public ArrayList<MemberVO> getMemberList(int startIndexNo, int pageSize);

	public ArrayList<MemberVO> getMidSearchList(int startIndexNo, int pageSize, String midSearch);

	public void setOutMember(String mid);

	public MemberVO getMemberId(String name, String tel);

	public int totTermRecCnt(String mid);

	public ArrayList<MemberVO> getTermMemberList(int startIndexNo, int pageSize, String mid);
}
