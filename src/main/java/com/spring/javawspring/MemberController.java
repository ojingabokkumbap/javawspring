package com.spring.javawspring;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.javawspring.pagination.PageProcess;
import com.spring.javawspring.pagination.PageVO;
import com.spring.javawspring.service.MemberService;
import com.spring.javawspring.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired //pagination처리
	PageProcess pageProcess;
	
	@RequestMapping(value = "/memberLogin", method=RequestMethod.GET)
	public String memberLoginGet(HttpServletRequest request) {
	// 로그인폼 호출 시에 기존에 저장된 쿠키가 있다면 불러와서 mid에 담아서 넘겨준다.
			Cookie[] cookies = request.getCookies();
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("cMid")) {
					request.setAttribute("mid", cookies[i].getValue());
					break;
				}
		}
		return "member/memberLogin";
	}
	
	@RequestMapping(value = "/memberLogin", method=RequestMethod.POST)
	public String memberLoginPost(HttpServletRequest request , HttpServletResponse response ,HttpSession session, //session 생성하기 위해 올려주기
			//null값 처리
			@RequestParam(name="mid", defaultValue = "" , required = false) String mid,
			@RequestParam(name="pwd", defaultValue = "" , required = false) String pwd, 
			@RequestParam(name="idCheck", defaultValue = "" , required = false) String idCheck) { 
		
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		if(vo != null && passwordEncoder.matches(pwd, vo.getPwd()) && vo.getUserDel().equals("NO")) { //정상적으로 로그인 된 회원
			//회원 인증처리 된 경우 수행할 내용 ? 
			// 1. strLevel처리 , 2. session에 필요한 자료 저장 , 3.쿠키값 처리 , 4.방문자 수/방문 포인트 증가
			
//		1. strLevel처리
			String strLevel = "";
			if(vo.getLevel() == 0) strLevel="관리자";
			else if(vo.getLevel() == 1) strLevel="운영자";
			else if(vo.getLevel() == 2) strLevel="우수회원";
			else if(vo.getLevel() == 3) strLevel="정회원";
			else if(vo.getLevel() == 4) strLevel="준회원";
			
//		2. session에 필요한 자료 저장
			session.setAttribute("sStrLevel", strLevel); //등급을 strLevel에 넣어주기
			session.setAttribute("sLevel", vo.getLevel());
//			session.setAttribute("sMid", mid);
			session.setAttribute("sMid", vo.getMid());
			session.setAttribute("sNickName", vo.getNickName());
			
//		 3.쿠키값 처리
			if(idCheck.equals("on")){
				Cookie cookie = new Cookie("cMid", mid);
				cookie.setMaxAge(60*60*24*7); //쿠키 만료시간 일주일
				response.addCookie(cookie);
			}
			else {
				Cookie[] cookies = request.getCookies();
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("cMid")) {
						cookies[i].setMaxAge(0); //쿠키삭제
						response.addCookie(cookies[i]);
						break;
					}
				}
			}
			
//		4.방문자 수/방문 포인트 증가
			memberService.setMemberVisitProcess(vo);
			
			return "redirect:/msg/memberLoginOk?mid="+mid;
		}
		else {
			return "redirect:/msg/memberLoginNo";
		}
	}
	

	
	//로그인하고 메인화면
	@RequestMapping(value = "/memberMain", method=RequestMethod.GET)
	public String memberMainGet(HttpSession session, Model model) {
		
		String mid = (String) session.getAttribute("sMid");
		
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		model.addAttribute("vo",vo);
		
		return "member/memberMain";
	}
	
//	로그아웃
	@RequestMapping(value="/memberLogout", method=RequestMethod.GET)
	public String memberLogoutGet(HttpSession session) {
	String mid = (String) session.getAttribute("sMid");
	
	session.invalidate();
	
	return "redirect:/msg/memberLogout?mid="+mid;
	}	
	
//	관리자 로그아웃
	@RequestMapping(value = "/adminLogout", method=RequestMethod.GET)
	public String adminLogoutGet(HttpSession session) {
		String mid = (String) session.getAttribute("sAMid");
		
		session.invalidate();
		
		return "redirect:/msg/memberLogout?mid="+mid;
	}
	
	//회원가입폼
	@RequestMapping(value = "/memberJoin", method = RequestMethod.GET)
	public String memberJoinGet() {
		
		return "member/memberJoin";
	}
	
	//회원가입처리
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public String memberJoinPost(MemberVO vo) {
//		System.out.println("mvo"+ vo);
		//아이디 중복체크(백에서 체크)
		if(memberService.getMemberIdCheck(vo.getMid()) != null) {
			return "redirect:/msg/memberIdCheckNo";
		}
		
		//닉네임 중복체크(백에서 체크)
		if(memberService.getMemberNickCheck(vo.getNickName()) != null) {
			return "redirect:/msg/memberNickNameCheckNo";
		}
		
		// 비밀번호 암호화(
		vo.setPwd(passwordEncoder.encode(vo.getPwd()));
		
		// 체크가 완료 되면 vo에 담긴 자료를 DB에 저장시켜준다(회원 가입)
		int res = memberService.setMemberJoinOk(vo);
		
		
		if(res == 1) 	return "redirect:/msg/memberJoinOk";
		else return "redirect:/msg/memberJoinNo";
		
	}
	
	//회원 탈퇴폼
	@RequestMapping(value = "/memberOutPwdCheck", method = RequestMethod.GET)
	public String memberOutPwdCheck() {
		return "member/memberOutPwdCheck";
	}

	
	//회원 탈퇴 비밀번호 확인
	@RequestMapping(value="/memberOutPwdCheck",method = RequestMethod.POST)
	public String memOutPwdCheck(String mid, String pwd) {
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		if(!passwordEncoder.matches(pwd, vo.getPwd())){
			return "redirect:/msg/memberOutPwdNo";
		}
		
		memberService.setOutMember(mid);
		return "redirect:/msg/memberOutPwdOk";
	} 
	
	
	//아이디 찾기 폼
	@RequestMapping(value = "/memberIdSearch", method = RequestMethod.GET)
	public String memberIdSearch() {
		return "member/memberIdSearch";
	}
	
	
	//아이디 찾기
	@ResponseBody
	@RequestMapping(value="/memberIdSearch",method = RequestMethod.POST)
	public String memberIdSearchPost(String name, String tel) {
		
        String res="0";
        
		MemberVO vo = memberService.getMemberId(name,tel);
		
		if(vo != null) {
			res = "1";
			return res;
		}
		else return "redirect:/msg/memberIdSearchNo";
    }
	
	
	//아이디 찾기 결과 폼
	@RequestMapping(value = "/memberIdSearchRes", method = RequestMethod.GET)
	public String memberIdSearchRes() {
		return "member/memberIdSearchRes";
	}
	
	//id중복체크 (프론트)
	@ResponseBody //ajax 사용 시  
	@RequestMapping(value = "/memberIdCheck", method = RequestMethod.GET)
	public String memberIdCheckPost(String mid) {
		String res = "0";
		
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		if(vo != null ) res = "1";
		return res;
	}
	
	//닉네임중복체크 (프론트)
	@ResponseBody 			//ajax 사용 시  
	@RequestMapping(value = "/memberNickCheck", method = RequestMethod.GET)
	public String memberNickCheckPost(String nickName) {
		String res = "0";
		MemberVO vo = memberService.getMemberNickCheck(nickName);
		
		if(vo != null ) res = "1";
		return res;
	}
	
	/*
	//전체 리스트와 검색리스트를 하나의 메소드로 처리(동적처리)
	@RequestMapping(value="/memberList", method = RequestMethod.GET)
	public String memberListGet(Model model,
			@RequestParam(name="pag", defaultValue="1", required = false) int pag,
			@RequestParam(name="pagSize", defaultValue="5", required = false) int pageSize, 
			@RequestParam(name="mid",defaultValue="", required = false) String mid){
			
		// 1.페이지(pag)를 결정한다.
		int totRecCnt = memberService.totTermRecCnt(mid);	// 3. 총 레코드 건수를 구한다.
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (totRecCnt / pageSize) + 1;  // 4. 총 페이지 건수를 구한다.
		int startIndexNo = (pag - 1) * pageSize;	// 5. 현재페이지의 시작 인덱스번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;	// 6. 현재 화면에 보여주는 시작번호를 구한다.
		
		int blockSize = 3;	// 1. 블록의 크기를 결정한다.(여기선 3으로 지정)
		int curBlock = (pag - 1) / blockSize;	// 2. 현재페이지가 위치하고 있는 블록 번호를 구한다.(예:1페이지는 0블록, 3페이지는 0블록, 5페이지는 1블록)
		int lastBlock = (totPage - 1) / blockSize;	// 3. 마지막블록을 구한다.
		
		ArrayList<MemberVO> vos = memberService.getTermMemberList(startIndexNo, pageSize, mid);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pag", pag);
		model.addAttribute("totPage", totPage);
		model.addAttribute("totRecCnt", totRecCnt);
		model.addAttribute("curScrStartNo", curScrStartNo);
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("curBlock", curBlock);
		model.addAttribute("lastBlock", lastBlock);
		
		model.addAttribute("mid",mid);
		
		return "member/memberList";
	}
	*/
	
	// Pagination 이용하기...
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public String memberListGet(Model model,
			@RequestParam(name="mid", defaultValue = "", required = false) String mid,
			@RequestParam(name="pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name="pageSize", defaultValue = "3", required = false) int pageSize) {
		
		PageVO pageVo = pageProcess.totRecCnt(pag, pageSize, "member", "", "");
		
		List<MemberVO> vos = memberService.getMemberList(pageVo.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		
		return "member/memberList";
	}
	
	/*
	//아이디검색
	@RequestMapping(value="/memMidSearchList", method = RequestMethod.POST)
	public String searchListPost(Model model, String mid,
			@RequestParam(name="pag", defaultValue="1", required = false) int pag,
			@RequestParam(name="pagSize", defaultValue="5", required = false) int pageSize,
			@RequestParam(name="mid",defaultValue="", required = false) String midSearch) {
		

		
		// 1.페이지(pag)를 결정한다.
		int totRecCnt = memberService.totRecCnt();	// 3. 총 레코드 건수를 구한다.
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (totRecCnt / pageSize) + 1;  // 4. 총 페이지 건수를 구한다.
		int startIndexNo = (pag - 1) * pageSize;	// 5. 현재페이지의 시작 인덱스번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;	// 6. 현재 화면에 보여주는 시작번호를 구한다.
		
		int blockSize = 3;	// 1. 블록의 크기를 결정한다.(여기선 3으로 지정)
		int curBlock = (pag - 1) / blockSize;	// 2. 현재페이지가 위치하고 있는 블록 번호를 구한다.(예:1페이지는 0블록, 3페이지는 0블록, 5페이지는 1블록)
		int lastBlock = (totPage - 1) / blockSize;	// 3. 마지막블록을 구한다.
		
		ArrayList<MemberVO> vos = memberService.getMidSearchList(startIndexNo, pageSize, midSearch);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pag", pag);
		model.addAttribute("totPage", totPage);
		model.addAttribute("curScrStartNo", curScrStartNo);
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("curBlock", curBlock);
		model.addAttribute("lastBlock", lastBlock);
		model.addAttribute("mid", midSearch);
		
		return "member/memMidSearchList";
	}
	*/
	
	//회원정보 상세보기
	@RequestMapping(value="/memberInfo",method = RequestMethod.GET)
	public String memberInfoGet(Model model, String mid) {
		
		MemberVO vo = memberService.getMemberIdCheck(mid);
		model.addAttribute("vo",vo);
		
		return "member/memberInfo";
	}
	

	
}
