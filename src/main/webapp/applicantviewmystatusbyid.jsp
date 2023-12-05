<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/png" href="/images/apple-touch-icon.png"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
<center>
hello
<c:forEach items="${jobslist}" var="job">
		  ${job.Applicationstatus}
				</c:forEach>
								</center>
								</h1>
</body>
</html>