package com.spring.javawspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.StudyDAO;
import com.spring.javawspring.vo.GuestVO;

//annotation필수
@Service
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	StudyDAO studyDAO; //interface생성

	@Override
	public String[] getCityStringArr(String dodo) {
		String[] strArr = new String[100];
		
		if(dodo.equals("서울")) {
			strArr[0] = "강남구";
			strArr[1] = "서초구";
			strArr[2] = "동대문구";
			strArr[3] = "마포구";
			strArr[4] = "관악구";
			strArr[5] = "강서구";
			strArr[6] = "강북구";
			strArr[7] = "중구";
			strArr[8] = "광진구";
			strArr[9] = "서구";
		}
		else if(dodo.equals("경기")) {
			strArr[0] = "수원시";
			strArr[1] = "이천시";
			strArr[2] = "화성시";
			strArr[3] = "용인시";
			strArr[4] = "일산시";
			strArr[5] = "광주시";
			strArr[6] = "평택시";
			strArr[7] = "안성시";
			strArr[8] = "의정부시";
			strArr[9] = "시흥시";
		}
		else if(dodo.equals("충북")) {
			strArr[0] = "청주시";
			strArr[1] = "괴산군";
			strArr[2] = "진천군";
			strArr[3] = "제천시";
			strArr[4] = "음성군";
			strArr[5] = "충주시";
			strArr[6] = "옥천군";
			strArr[7] = "영동군";
			strArr[8] = "증평군";
			strArr[9] = "단양군";
		}
		else if(dodo.equals("충남")) {
			strArr[0] = "천안시";
			strArr[1] = "병천시";
			strArr[2] = "옥산군";
			strArr[3] = "공주시";
			strArr[4] = "아산시";
			strArr[5] = "당진군";
			strArr[6] = "보령시";
			strArr[7] = "계룡시";
			strArr[8] = "논산시";
			strArr[9] = "예산군";
		}
		
		return strArr;
	}

	@Override
	public ArrayList<String> getCityArrayListArr(String dodo) {
		ArrayList<String> vos = new ArrayList<String>();
		
		if(dodo.equals("서울")) {
			vos.add("강남구");
			vos.add("서초구");
			vos.add("동대문구");
			vos.add("마포구");
			vos.add("관악구");
			vos.add("강서구");
			vos.add("강북구");
			vos.add("중구");
			vos.add("광진구");
			vos.add("서구");
		}
		else if(dodo.equals("경기")) {
			vos.add("수원시");
			vos.add("이천시");
			vos.add("화성시");
			vos.add("용인시");
			vos.add("일산시");
			vos.add("광주시");
			vos.add("평택시");
			vos.add("안성시");
			vos.add("시흥시");
			vos.add("의정부시");
		}
		else if(dodo.equals("충북")) {
			vos.add("청주시");
			vos.add("괴산군");
			vos.add("진천군");
			vos.add("제천시");
			vos.add("음성군");
			vos.add("충주시");
			vos.add("옥천군");
			vos.add("영동군");
			vos.add("증평군");
			vos.add("단양군");
		}
		else if(dodo.equals("충남")) {
			vos.add("천안시");
			vos.add("병천시");
			vos.add("옥산군");
			vos.add("공주시");
			vos.add("아산시");
			vos.add("당진군");
			vos.add("보령시");
			vos.add("계룡시");
			vos.add("논산시");
			vos.add("예산군");
		}
		
		return vos;
	}

	@Override
	public GuestVO getGuestMid(String mid) {
		return studyDAO.getGuestMid(mid);
	}

	@Override
	public ArrayList<GuestVO> getGuestNames(String mid) {
		return studyDAO.getGuestNames(mid);
	}
}
