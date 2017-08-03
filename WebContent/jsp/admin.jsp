<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@page import="java.util.ArrayList"%>
<%@page import="team.project.dto.Admin"%>
<%
	Admin adminToday = (Admin)request.getSession().getAttribute("adminToday");
%>
<script src="../js/jquery-3.2.1.js"></script>
<script>
$(function() {
	//아작스로 구현
	$("#write").click(function() {
		$.ajax({
			url : "adminajax.puppy",
			dataType : "json",//가지고 오는 데이터의 타입//"json", "xml" 도 가능
			success : function(data) {//호출 성공시
				console.log("호출성공"+data);
				var str;
				$.each(data, function(index, item) {
					str = str + "<tr>";
					str = str + "<td>오늘의 등록된 동물수</td>";
					str = str + "<td>오늘의 가입자수</td>";
					str = str + "<td>오늘의 방문자수</td>";
					str = str + "<td>총 방문자수</td>";
					str = str + "<td>오늘의 게시글수</td>";
					str = str + "</tr>";
					str = str + "<tr>";
					str = str + "<td>" + item.puppyTodayCount + "</td>";
					str = str + "<td>" + item.regToayCount + "</td>";
					str = str + "<td>" + item.visitTodayCount + "</td>";
					str = str + "<td>" + item.visitTotalCount + "</td>";
					str = str + "<td>" + item.postTodayCount + "</td>";
					str = str + "</tr>";
				});
				$("#value").html("<table border='2px'>" +str + "</table>");

			},
			error : function() {
				console.log("error");
			}
		});
	});
});

</script>
</head>
<body>
	오늘의 등록된 동물수<%out.print(adminToday.getPuppyTodayCount());%>
	<br>
	총 등록된 동물수<%out.print(adminToday.getPuppyTotalCount());%>
	<br>
	오늘의 가입자수<%out.print(adminToday.getRegTodayCount());%>
	<br>
	총 가입자수<%out.print(adminToday.getRegTotalCount());%>
	<br>
	오늘의 방문자수<%out.print(adminToday.getVisitTodayCount());%>
	<br>
	총 방문자수<%out.print(adminToday.getVisitTotalCount());%>
	<br>
	오늘의 게시글수<%out.print(adminToday.getPostTodayCount());%>
	<br>
	총 게시글수<%out.print(adminToday.getRegTotalCount());%>
	<br>
	
	
	
	<input type="button" id="write">
	<fieldset>
		<legend>내용</legend>
		<span id="value">
	</fieldset>
</body>
</html>