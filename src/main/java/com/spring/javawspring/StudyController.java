package com.spring.javawspring;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.javawspring.common.ARIAUtil;
import com.spring.javawspring.common.SecurityUtil;
import com.spring.javawspring.service.MemberService;
import com.spring.javawspring.service.StudyService;
import com.spring.javawspring.vo.GuestVO;
import com.spring.javawspring.vo.MailVO;
import com.spring.javawspring.vo.MemberVO;

//annotation필수
@Controller
//중간경로설정
@RequestMapping("/study") 
public class StudyController {
	
	@Autowired
	StudyService studyService; //1.service
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	MemberService meberService;
	
	@RequestMapping(value="/ajax/ajaxMenu" , method= RequestMethod.GET)
	public String ajaxMenuGet() {
		return "study/ajax/ajaxMenu";
	}
	
	//일반 String 값의 전달 1(숫자/영문자)
	@ResponseBody //string 형식의 단어를 주고받게 해주는 annotation
	@RequestMapping(value="/ajax/ajaxTest1_1", method=RequestMethod.POST)
	public String ajaxTest1_1Post(int idx) {
		idx = (int) (Math.random()*idx) + 1; //난수생성
		String res = idx + " : Happiness is Mine ";
		return res;
	}
	
	//일반 String 값의 전달 2(숫자/영문자/한글)
	@ResponseBody //string 형식의 단어를 주고받게 해주는 annotation                       //사용하는 응용프로그램 형식 (한글설정)
	@RequestMapping(value="/ajax/ajaxTest1_2", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	public String ajaxTest1_2Post(int idx) {
		idx = (int) (Math.random()*idx) + 1; //난수생성
		String res = idx + " : 행복은 나의 것! Happiness is Mine ";
		return res;
	}
	
//일반 배열 값의 전달 폼
	@RequestMapping(value="ajax/ajaxTest2_1", method = RequestMethod.GET) 
		public String ajaxTest2_1() {
			return "study/ajax/ajaxTest2_1";
		}
	
//일반 배열 값의 전달
	@ResponseBody
	@RequestMapping(value="ajax/ajaxTest2_1", method = RequestMethod.POST) 
	public String[] ajaxTest2_1Post(String dodo) {
//		System.out.println("dodo"+dodo);
//		String[] strArr = new String[100];
//		strArr = studyService.getCityStringArr(dodo);
//		return strArr;
		return studyService.getCityStringArr(dodo);
	}
	
	
	
	
	
//객체 배열(arrayList) 값의 전달폼
	@RequestMapping(value="ajax/ajaxTest2_2", method = RequestMethod.GET) 
	public String ajaxTest2_2Get() {
		return "study/ajax/ajaxTest2_2";
	}
	
//객체 배열(arrayList) 값의 전달
	@ResponseBody
	@RequestMapping(value="ajax/ajaxTest2_2", method = RequestMethod.POST) 
	public ArrayList<String> ajaxTest2_2Post(String dodo) {
		return studyService.getCityArrayListArr(dodo);
	}
	
	
	
	
	
//Map(HashMap<k,v>) 값의 전달폼
	@RequestMapping(value="ajax/ajaxTest2_3", method = RequestMethod.GET) 
	public String ajaxTest2_3Get() {
		return "study/ajax/ajaxTest2_3";
	}
	
//Map(HashMap<key,value>) 값의 전달
	@ResponseBody
	@RequestMapping(value="ajax/ajaxTest2_3", method = RequestMethod.POST) 
	public HashMap<Object, Object> ajaxTest2_3Post(String dodo) {
		ArrayList<String> vos = new ArrayList<String>();
		
		vos = studyService.getCityArrayListArr(dodo);
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		        //value 부르기
		map.put("city", vos);
		
		return map;
	}
	
	
	

	
	//DB를 활용한 값 전달 폼
	@RequestMapping(value= "/ajax/ajaxTest3", method = RequestMethod.GET)
	public String ajaxTest3Get() {
		return "study/ajax/ajaxTest3";
	}
	
	//DB를 활용한 값 전달1 (vo)
	@ResponseBody
	@RequestMapping(value= "/ajax/ajaxTest3_1", method = RequestMethod.POST)
	public GuestVO ajaxTest3_1Post(String mid) {
//		GuestVO vo = studyService.getGuestMid(mid);
//		return vo;
		return studyService.getGuestMid(mid);
	}
	
	//DB를 활용한 값 전달2 (vos)
	@ResponseBody
	@RequestMapping(value= "/ajax/ajaxTest3_2", method = RequestMethod.POST)
	public ArrayList<GuestVO> ajaxTest3_2Post(String mid) {
//		GuestVO vos = studyService.getGuestNames(mid);
//		return vos;
		return studyService.getGuestNames(mid);
	}

	
	
	//암호화연습sha256
	@RequestMapping(value="/password/sha256" , method = RequestMethod.GET)
	public String sha256get() {
		return "study/password/sha256";
	}
	
	//암호화 연습 결과처리sha256
	@ResponseBody
	@RequestMapping(value="/password/sha256" , method = RequestMethod.POST , produces = "application/text; charset=utf8")
	public String sha256Post(String pwd) {
		String encPwd = SecurityUtil.encryptSHA256(pwd);
		pwd = "원본 비밀번호" + pwd + " / 암호화 된 비밀번호 : " + encPwd;
		
		return pwd;
	}
	
	
	
	
	
	//암호화연습aria
	@RequestMapping(value="/password/aria" , method = RequestMethod.GET)
	public String ariaGet() {
		return "study/password/aria";
	}
	
	//암호화 연습 결과처리aria
	@ResponseBody
	@RequestMapping(value="/password/aria" , method = RequestMethod.POST , produces = "application/text; charset=utf8")
	public String ariaPost(String pwd) {
		
		String encPwd = null;
		String decPwd = null;
		
		try {
			encPwd = ARIAUtil.ariaEncrypt(pwd); //암호화
			decPwd = ARIAUtil.ariaDecrypt(encPwd); //복호화
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		pwd = "원본 비밀번호 : " + pwd + " / 암호화 된 비밀번호 : " + encPwd + " / 복호화 된 비밀번호 : " + decPwd;
		return pwd;
	}
	
	
	
	
	
	//암호화연습(bCryptcheck)
	@RequestMapping(value="/password/bCryptPassword" , method = RequestMethod.GET)
	public String bCryptPasswordGet() {
		return "study/password/security";
	}
	
	//암호화 연습 결과처리(bCryptcheck)
	@ResponseBody
	@RequestMapping(value="/password/bCryptPassword" , method = RequestMethod.POST , produces = "application/text; charset=utf8")
	public String bCryptPasswordPost(String pwd) {
		
		String encPwd = null;
		encPwd = passwordEncoder.encode(pwd);
				
		pwd = "원본 비밀번호 : " + pwd + " / 암호화 된 비밀번호 : " + encPwd ;
		return pwd;
	}
	
	// Simple Mail Transfer Protocol메일보내기
	// 메일 작성 폼
	@RequestMapping(value= "/mail/mailForm" , method = RequestMethod.GET)
	public String mailFormGet(Model model, String email) {
		
		List<MemberVO> vos = meberService.getMemberList(0, 1000);
		
		model.addAttribute("vos",vos);
		model.addAttribute("email",email);
		model.addAttribute("cnt",vos.size());
		
		return "study/mail/mailForm";
	}
	
	//주소록 호춯하기(모달사용)
	
	//메일 전송
	@RequestMapping(value= "/mail/mailForm" , method = RequestMethod.POST)
	public String mailFormPost(MailVO vo , HttpServletRequest request) {
		try {
			
			String content = vo.getContent();
			
			//메일은 전송하기 위한 객체 : MimeMessage() , 보관함 : MimeMessageHelper()
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			//메일 보관함에 회원이 보내온 메시지들을 모두 저장한다.
			messageHelper.setTo(vo.getToMail());
			messageHelper.setSubject(vo.getTitle());
			messageHelper.setText(vo.getContent());
			
			//메시지 보관함의 내용(content)에 필요한 정보를 추가로 담아 전송할 수 있게 한다.
			content = vo.getContent().replace("\n","<br/>");
			content += "<br><hr><h3>안녕하세요...</h3><hr><br>";
			content += "<p><img src=\"cid:main.png\" width='500px'></p>";
			content += "<p>방문하기 : <a href='http://49.142.157.251:9090/green2209J_02/LeagueOfLegend.jsp'>jsp프로젝트</a></p>";
			content += "<hr>";
			messageHelper.setText(content, true);
			
			// 본문에 기재된 그림파일의 경로를 따로 표시시켜준다. 그리고, 보관함에 다시 저장시켜준다.
			FileSystemResource file = new FileSystemResource("D:\\JavaWorkspace\\springframework\\works\\javawspring\\src\\main\\webapp\\resources\\images\\main.png");
			messageHelper.addInline("main.png", file);
			
			
			// 첨부파일 보내기 서버 파일시스템에 잇는 파일
			file = new FileSystemResource("D:\\JavaWorkspace\\springframework\\works\\javawspring\\src\\main\\webapp\\resources\\images\\9.jpg");
			messageHelper.addAttachment("9.jpg", file);
			
			// 첨부파일 보내기 서버 파일시스템에 잇는 파일
			file = new FileSystemResource("D:\\JavaWorkspace\\springframework\\works\\javawspring\\src\\main\\webapp\\resources\\images\\images.zip");
			messageHelper.addAttachment("images.zip", file);
			
			// file = new FileSystemResource(request.getRealPath("/resources/images/5.jpg"));
			file = new FileSystemResource(request.getSession().getServletContext().getRealPath("/resources/images/5.jpg"));
			messageHelper.addAttachment("5.jpg", file);
			
			//메일 전송
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "redirect:/msg/mailSendOk";
	}
	
		//UUID 입력폼
		@RequestMapping(value="/uuid/uuidForm", method=RequestMethod.GET)
		public String uuidFormGet() {
			return "study/uuid/uuidForm";
		}
		
		
		//UUID처리
		@ResponseBody
		@RequestMapping(value="/uuid/uuidProcess", method=RequestMethod.POST)
		public String uuidFormPost() {
			UUID uid = UUID.randomUUID(); //uuid타입
			
			return uid.toString();
		}
}
