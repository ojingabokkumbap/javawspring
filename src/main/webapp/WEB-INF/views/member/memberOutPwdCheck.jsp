<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memberList.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script>
  	'use strict';
  	
  	/* 삭제 확인 */
  	$(document).ready(function(){
  		$("#btnDelete").click(function(){
  	    let pwd = myform.pwd.value;
 	      if(pwd.trim() == "") {
 	    	  alert("비밀번호를 입력하세요.");
 	    	  myform.pwd.focus();
 	      }
 	      else {
	  		  let ans = confirm("정말로 탈퇴하시겠습니까?");
	  			if(ans) {
		  		  ans = confirm("탈퇴 후 1개월 간은 같은 아이디로 가입하실 수 없습니다.\n탈퇴 하시겠습니까?");
		  		  if(ans) document.myform.action ="${ctp}/member/memberOutPwdCheck";
	           				document.myform.submit();
	  	  	}
 	      }
  		});
  	});
  	
  </script>
</head>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <form name="myform" method="post" class="was-validated">
	  <h2 class="text-center">회원 탈퇴 비밀번호 확인</h2>
	  <br/>
	  <table class="table table-bordered">
	    <tr>
	      <th>비밀번호</th>
	      <td>
	        <input type="password" name="pwd" id="pwd" autofocus required class="form-control"/>
	        <div class="invalid-feedback">비밀번호를 입력하세요.</div>
	      </td>
	    </tr>
	    <tr>
	      <td colspan="2" class="text-center">
	        <input type="button" value="확인"  id="btnDelete" class="btn btn-success"/> &nbsp;
	        <input type="reset" value="다시입력" class="btn btn-warning"/> &nbsp;
	        <input type="button" value="돌아가기" onclick="location.href='${ctp}/member/memberMain';" class="btn btn-secondary"/>
	      </td>
	    </tr>
	  </table>
	  <input type="hidden" name="mid" value="${sMid}"/>
  </form>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>