<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>uuidForm</title>
    <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
</head>
<script>
	'use strict';
	let str = "";
	let cnt =0;
	
	function uuidCheck(){
		$.ajax({
			type  : "post",
			url   : "${ctp}/study/uuid/uuidProcess",
			success : function(res){
				cnt++;
				str += cnt + " : " + res + "<br/>";
				$("#demo").html(str);
			},
			error : function(){
				alert("전송오류");
			}
		});
	}
</script>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
	<div class="container">
		<h2>UUID에 대해</h2>
		<p>
			UUID(Universally Unique IDentifier)란 , 네트워크상에서 고유성이 보장되는 id를 만들기 위한 규약<br/>
			32자리의 16진수 (128bit)로 표현된다.<br/>
			표시 : 8-4-4-4-12 자리로 표현한다.<br/>
			예 : 55edt624-13r4-fe34-14a1-d25fs3t35244
		</p>
		<p>
			<input type="button" value="UUID생성" onclick="uuidCheck()" class="btn btn-outline-success"/>
			<input type="button" value="다시하기" onclick="location.reload()" class="btn btn-outline-info"/>
		</p>
    <div>
    	출력결과 <br/>
    	<span id="demo"></span>
    </div>
  </div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>
