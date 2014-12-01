<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
 
<body>
<head>
	<title>Main Page</title>
	
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

<table>
<tr><th>Web based</th><th>REST based</th></tr>
<tr><td><a href="./home">Home</a></td><td><a href="./users">Users</a></td></tr>
<tr><td><a href="./users/MV">Users ModelView</a></td><td><a href="./user/1">User/1</a></td></tr>
<tr><td><a href="./users/new">New User</a></td></tr>
</table>
</body>
</html>