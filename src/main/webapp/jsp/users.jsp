<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Users Page</title>
	
<style>
 table, th, td {
   border: 1px solid black;
   border-collapse: collapse;
   }
 th,td {
   padding: 5px;
   }
</style>
</head>
<body>
<h1>Users DB Info</h1>

<table>
<tr><th>ID</th><th>Username</th><th>Password</th><th colspan=2>Action</th></tr>
<c:forEach var="user" items="${users}">
	<tr>
		<td>${user.id}</td><td>${user.username}</td><td>${user.password}</td>
		<td><a href="<c:url value='./update/${user.id}' />" >Update</a></td>
        <td><a href="<c:url value='./delete/${user.id}' />" >Delete</a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>