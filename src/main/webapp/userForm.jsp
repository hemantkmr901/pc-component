<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>UserForm</title>
<style type="text/css">
fieldset {
	border: 1px solid #dedede;
}

legend {
	font-size: 20px;
	text-transform: uppercase;
}

.error {
	color: red;
}

.resltTable {
	width: 50%;
	border-collapse: collapse;
	border-spacing: 0px;
}

.resltTable td, .resltTable th {
	border: 1px solid #565454;
}
</style>
</head>
<body>
	<fieldset>
		<legend>User Input From</legend>
		<form:form action="saveText" method="post" modelAttribute="user">
			<table>
				<tr>
					<th>Name</th>
					<td><form:input path="name" /> <form:errors path="name"
							cssClass="error" /></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><form:input path="email" /> <form:errors path="email"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td><button type="submit">Submit</button></td>
				</tr>
			</table>
		</form:form>
	</fieldset>
	<br>
	<br>

	<fieldset>
		<legend>Users List</legend>
		<table class="resltTable">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Image</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td><img src="${user.photoLink}" width="100" height="200"></td>
					<td><a href='getById?id=${user.id}'>Update</a></td>
					<td><a href='deleteById?id=${user.id}'>Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
</body>
</html>