<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="fileUpdate" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<th>Existing Image</th>
				<td><img src="<%=request.getParameter("photoLink") %>" width="100" height="200"></td>
			</tr>
			<tr>
				<th>New Image</th>
				<td><input type="file" name="file" /></td>
			</tr>

			<tr>
				<td><button type="submit">Submit</button></td>
			</tr>
		</table>
	</form>
</body>
</html>