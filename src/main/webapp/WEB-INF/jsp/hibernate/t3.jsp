<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>Cart</h1>
		<p>Id:<span>${cart.id}</span></p>
		<p>Name:<span>${cart.name}</span></p>
		<p>Total:<span>${cart.total}</span></p>		
	</div>	
	<div>
		<h1>Items</h1>
		<c:forEach items="${cart.items}" var="item">
			<p>Id:<span>${item.id}</span></p>
			<p>Item Id:<span>${item.itemId}</span></p>
			<p>Total Item:<span>${item.itemTotal}</span></p>	
			<p>Quantity:<span>${item.quantity}</span></p>	
		</c:forEach>			
	</div>
</body>
</html>