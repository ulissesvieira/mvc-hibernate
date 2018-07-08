<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>Cart</h1>
		<p>Id:<span>${cart.id}</span></p>		
		<p>Total:<span>${cart.total}</span></p>		
	</div>	
	<div>
		<h1>Items</h1>
		<c:forEach items="${cart.items}" var="item">
			<p>Id:<span>${item.id}</span></p>
			<p>Description:<span>${item.description}</span></p>
			<p>Price:<span>${item.price}</span></p>				
		</c:forEach>			
	</div>
</body>
</html>