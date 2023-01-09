package com.spring.javawspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
	@RequestMapping(value="/msg/{msgFlag}", method=RequestMethod.GET)
	public String msgGet(@PathVariable String msgFlag, Model model,
			@RequestParam(value="mid", defaultValue = "", required = false) String mid) {
		
		if(msgFlag.equals("guestInputOk")) {
			model.addAttribute("msg", "방명록에 글이 등록되었습니다.");
			model.addAttribute("url", "guest/guestList");
		}
		else if(msgFlag.equals("memberLoginOk")) {
			model.addAttribute("msg", mid+"님 로그인 하셨습니다.");
			model.addAttribute("url", "member/memberMain");
		}
		else if(msgFlag.equals("memberLoginNo")) {
			model.addAttribute("msg", "아이디 또는 비밀번호가 틀립니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberLogout")) {
			model.addAttribute("msg", mid+"님 로그아웃 하셨습니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("guestDeleteOk")) {
			model.addAttribute("msg", "글이 삭제되었습니다.");
			model.addAttribute("url", "guest/guestList");
		}
		else if(msgFlag.equals("memberJoinOk")) {
			model.addAttribute("msg", "회원 가입이 완료되었습니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("membeIdCheckNo")) {
			model.addAttribute("msg", "중복된 아이디입니다.");
			model.addAttribute("url", "member/memberJoin");
			
		}
		else if(msgFlag.equals("memberNickNameCheckNo")) {
			model.addAttribute("msg", "중복된 닉네임입니다.");
			model.addAttribute("url", "member/memberJoin");
		}
		else if(msgFlag.equals("adminNo")) {
			model.addAttribute("msg", "관리자만 이용할 수 있습니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberNo")) {
			model.addAttribute("msg", "회원만 이용할 수 있습니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("levelCheckNo")) {
			model.addAttribute("msg", "이용할 수 없는 등급입니다.");
			model.addAttribute("url", "member/memberMain");
		}
		else if(msgFlag.equals("mailSendOk")) {
			model.addAttribute("msg", "메일이 전송되었습니다.");
			model.addAttribute("url", "study/mail/mailForm");
		}
		else if(msgFlag.equals("memberOutPwdOk")) {
			model.addAttribute("msg", "회원탈퇴 처리되었습니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberOutPwdNo")) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("url", "member/memberOutPwdCheck");
		}
		else if(msgFlag.equals("memberIdSearchNo")) {
			model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
			model.addAttribute("url", "member/memberIdSearch");
		}

		
		return "include/message";
	}
}
	