<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>Transaction</h1>
		<p>Id:<span>${transaction.id}</span></p>
		<p>Date:<span>${transaction.date}</span></p>
		<p>Total:<span>${transaction.total}</span></p>		
	</div>
	<div>
		<h1>Customer</h1>
		<p>Id:<span>${customer.id}</span></p>
		<p>Name:<span>${customer.name}</span></p>
		<p>Email:<span>${customer.email}</span></p>	
		<p>Address:<span>${customer.addresss}</span></p>		
	</div>
</body>
</html>