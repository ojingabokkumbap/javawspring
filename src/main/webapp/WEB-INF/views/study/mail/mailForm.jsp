<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Mail</title>
    <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
</head>
<style>
	.container{
		text-align : center;
	}
	td{
		background-color : white;
	}
</style>
<script>
	'use strict';
	
	function jusorokView(){
		$("#myModal").on("show.bs.modal",function(e){
			 $(".modal-header #cnt").html(${cnt});
			 	let jusorok = '';
			 	jusorok +='<table class="table table-hover">';
			 	jusorok +='<tr class="table-dark text-dark text-center">';
			 	jusorok +='<th>번호</th><th>아이디</th><th>성명</th><th>이메일주소</th>';
			 	jusorok +='</tr>';
			 	jusorok +='<c:forEach var="vo" items="${vos}" varStatus="st">';
			 	jusorok +='<tr onclick="location.href=\'${ctp}/study/mail/mailForm?email=${vo.email}\';" class="text-center">';
			 	jusorok +='<td>${st.count}</td>';
			 	jusorok +='<td>${vo.mid}</td>';
			 	jusorok +='<td>${vo.name}</td>';
			 	jusorok +='<td>${vo.email}</td>';
			 	jusorok +='</tr>';
			 	jusorok +='</c:forEach>';
			 	jusorok +='</table>';
			 $(".modal-body #jusorok").html(jusorok);
		});
	}

</script>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
	<div class="container">
    <h2 >메일 보내기</h2>
    <p>받는 사람의 메일 주소를 정확히 입력해 주세요.</p>
    <form name="myform" method="post">
    	<table class="table table-warning">
    		<tr>
    			<th>받는 사람</th>
    			<td>
    			<div class="row">
	    			<div class="col-10"><input type="text" value="${email}" name="toMail" placeholder="받는 사람의 메일 주소를 입력하세요." class="form-control" required></div>
	    			<div class="col-2"><input type="button" value="주소록" onclick= "jusorokView()" class="btn btn-warning form-control" data-toggle="modal" data-target="#myModal"/></div>
    			</div>
    			</td>
    		</tr>
    		<tr>
    			<th>메일 제목</th>
    			<td><input type="text" name="title" placeholder="메일 제목을 입력하세요." class="form-control" required></td>
    		</tr>
    		<tr>
    			<th>메일 내용</th>
    			<td><textarea rows="7" name="content" class="form-control" required></textarea> </td>
    		</tr>
    		<tr>
    			<td colspan="2" class="text-center">
    				<input type="submit" value="메일 보내기" class="btn btn-success"/>
    				<input type="reset" value="다시 쓰기" class="btn btn-primary"/>
    				<input type="button" value="돌아가기" onclick="location.href='${ctp}/member/memberMain';" class="btn btn-info"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </div>
  
  <!-- 주소록을 Modal로 출력하기  -->
  <div class="modal fade" id="myModal" style="width: 700px">
  	<div class="modal-dialog">
			<div class="modal-content" style="width: 550px">
				<div class="modal-header" style="width: 550px">
					<h4 class="modal-title">주소록(<span id="cnt"></span>건)</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" style="width: 550px; height:400px; overflow:auto;">
					<span id="jusorok"></span>
				</div>
				<div class="modal-footer" style="width: 550px">
					<button type="button" class="close" data-dismiss="modal">CLOSE</button>
				</div>
			</div>  	
  	</div>
  </div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>
