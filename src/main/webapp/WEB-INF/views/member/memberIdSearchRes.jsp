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
    
    function sendCheck() {
    	window.close();
    }
  </script>
</head>
</head>
<body>
<p><br/></p>
	<div class="container">
		<h3>등록하신 정보 조회 결과 <br/> 아이디는 ${res} 입니다.</h3>
		 <p><input type="button" class="btn btn-danger" value="창닫기" onclick="sendCheck()"/></p>
	</div>
<p><br/></p>
</body>
</html>