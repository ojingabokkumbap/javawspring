<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>title</title>
    <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
</head>
<script>
	'use strict';
	let str = "";
	let cnt = 0;
	
	function ariacheck(){
		let pwd = $("#pwd").val();
		
		$.ajax({
			type : "post",
			url  : "${ctp}/study/password/aria",
			data : {pwd : pwd},
			success : function(res){
				cnt++;
				str += cnt + " : " + res + "<br/>"
				$("#demo").html(str);
			}
		});
	}
	
</script>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
	<div class="container">
    <h2>ARIA 암호화</h2>
    <p>
    	ARIA 암호화 방식은 경량환경 및 하드웨어 구현을 위해 최적회된 Involutional SPN 구조를 갖는 범용블록 암호화이다.<br/>
    	ARIA가 사용하는 연산은 대부분 XOR과 같은 단순한 바이트단위연산으로, 블록크기는 128bit이다.<br/>
    	ARIA는 Academy(학계), Research Institute(연구소) , Agency(정부기관)의 첫글자를 따서 만들었다.<br/>
    	그래서 ARIA개발에 참여한 '학/연/관'의 공동노력을 포함한다.<br/>
    	ARIA암호화는 복호화가 가능하다.
    </p>
    <hr/>
    <p>
    	<input type="text" name="pwd" id="pwd" autofocus />
    	<input type="button" value="ARIA암호화" onclick="ariacheck()" class="btn-primary btn-sm" />
    	<input type="button" value="다시하기" onclick="location.reload()" class="btn-warning btn-sm" />
    </p>
		<hr/>
   출력결과 : <br/>
   <span id="demo"></span>
  </div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>
