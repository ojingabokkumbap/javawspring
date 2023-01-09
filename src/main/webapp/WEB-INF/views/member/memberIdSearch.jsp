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
/*      var url = "";
     var windowTargetName = "targetName";
     var features = "scrollbars=yes,width=400,height=200,location=no, resizable=yes";
     window.open(url, windowTargetName, features);

     // 2.POST로 데이터 전달
     myform.action=""; // 이동
     myform.method="post";
     myform.target=windowTargetName;
     myform.submit(); */
     
  	'use strict';
		function midSearch(){
		 let name = myform.name.value;
     let tel = myform.tel.value;
     
     $.ajax({
         type  :"post",
         url   : "${ctp}/member/memberIdSearch",
         data  : {name:name,
                 tel:tel},
         success : function(res){
	      	 if(res== "1"){
          		window.open("${ctp}/member/memberIdSearchRes?res="+res,"nWin","width=400px,height=200px");
			 				$("#name").focus();
	 						}
	         	else{
			 				alert("회원정보가 존재하지 않습니다.");
           }
         },
         error : function(){
             alert("전송실패");
         }            
     });  
			
		}
	
  </script>
</head>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <form name="myform" method="post" class="was-validated">
	  <h2 class="text-center">아이디 찾기</h2>
	  <p class="text-center">회원정보에 등록한 휴대전화 번호와 이름을 입력하면 아이디를 찾을 수 있습니다.</p>
	  <br/>
	  <table class="table table-bordered">
	    <tr>
	      <td>이름</td>
	     	<td>
	        <input type="text" name="name" id="name" autofocus required class="form-control" placeholder="이름을 입력하세요."/>
	       	<div class="invalid-feedback">이름은 필수 입력사항입니다.</div>
	      </td>
	    </tr>
	    <tr>
	      <td>전화번호</td>
	      <td>
	      <div>
		      <input type="text" name="tel" class="form-control" required placeholder="전화번호를 입력하세요. 예) 010-0000-0000"/>
		     	<div class="invalid-feedback">전화번호는 필수 입력사항입니다.</div>
				</div>
	      </td>
			</tr>
	    <tr>
	      <td colspan="2" class="text-center">
	        <input type="button" value="확인" onclick="midSearch()" class="btn btn-success"/> &nbsp;
	        <input type="reset" value="다시입력" class="btn btn-warning"/> &nbsp;
	        <input type="button" value="돌아가기" onclick="location.href='${ctp}/member/memberLogin';" class="btn btn-secondary"/>
	      </td>
	    </tr>
	  </table>
  </form>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>