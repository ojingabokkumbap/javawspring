package com.spring.javawspring.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.MemberVO;

public interface MemberDAO {

	public MemberVO getMemberIdCheck(@Param ("mid") String mid);

	public MemberVO getMemberNickCheck(@Param ("nickName") String nickName);

	public int setMemberJoinOk(@Param ("vo") MemberVO vo);

	public void setMemTotalUpdate(@Param ("mid") String mid , @Param ("nowTodayPoint") int nowTodayPoint, @Param ("todayCnt") int todayCnt);

	public int totRecCnt();

	public ArrayList<MemberVO> getMemberList(@Param ("startIndexNo") int startIndexNo, @Param ("pageSize") int pageSize);

	public ArrayList<MemberVO> getMidSearchList(@Param ("startIndexNo") int startIndexNo,  @Param ("pageSize") int pageSize, @Param ("midSearch") String midSearch);

	public boolean setOutPwdCheck(@Param ("mid") String mid, @Param("pwd") String pwd);

	public void setOutMember(@Param("mid") String mid);

	public MemberVO getMemberId (@Param("name") String name, @Param("tel") String tel);

	public int totTermRecCnt(@Param("mid") String mid);

	public ArrayList<MemberVO> getTermMemberList(@Param ("startIndexNo") int startIndexNo,  @Param ("pageSize") int pageSize, @Param ("mid") String mid);
	
}
