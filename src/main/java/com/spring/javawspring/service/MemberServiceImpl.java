package com.spring.javawspring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.MemberDAO;
import com.spring.javawspring.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public MemberVO getMemberIdCheck(String mid) {
		return  memberDAO.getMemberIdCheck(mid);
	}

	@Override
	public MemberVO getMemberNickCheck(String nickName) {
		return memberDAO.getMemberNickCheck(nickName);
	}

	@Override
	public int setMemberJoinOk(MemberVO vo) {
		return memberDAO.setMemberJoinOk(vo);
	}

	@Override
	public void setMemberVisitProcess(MemberVO vo) {
		//오늘 날짜 편집
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String strNow = sdf.format(now);
		
		//오늘 처음 방문 시는 오늘 방문 카운트(todayCnt)를 0으로 세팅
		if(!vo.getLastDate().substring(0,10).equals(strNow)) {
			
			// memberDAO.setTodayCntUpdate(vo.getMid());
			vo.setTodayCnt(0);
		}
			int todayCnt = vo.getTodayCnt() + 1;
		
		int nowTodayPoint = 0;
		if(vo.getTodayCnt() >= 5) { //5번 넘으면 포인트 증가X
			nowTodayPoint = vo.getPoint();
		}
		else { 
			nowTodayPoint = vo.getPoint() +10;
		}
		//재방문이면 '총방문','오늘방문','포인트' 누적처리
		memberDAO.setMemTotalUpdate(vo.getMid() , nowTodayPoint ,todayCnt);
	}

	@Override
	public int totRecCnt() {
		return memberDAO.totRecCnt();
	}

	@Override
	public ArrayList<MemberVO> getMemberList(int startIndexNo, int pageSize) {
		return memberDAO.getMemberList(startIndexNo, pageSize);
	}

	@Override
	public ArrayList<MemberVO> getMidSearchList(int startIndexNo, int pageSize, String midSearch) {
		return memberDAO.getMidSearchList(startIndexNo, pageSize, midSearch);
	}

	@Override
	public void setOutMember(String mid) {
		memberDAO.setOutMember(mid);
	}

	@Override
	public MemberVO getMemberId(String name, String tel) {
		return memberDAO.getMemberId(name, tel);
	}

	@Override
	public int totTermRecCnt(String mid) {
		return memberDAO.totTermRecCnt(mid);
	}

	@Override
	public ArrayList<MemberVO> getTermMemberList(int startIndexNo, int pageSize, String mid) {
		return memberDAO. getTermMemberList(startIndexNo,pageSize, mid) ;
	}

	

	
}
