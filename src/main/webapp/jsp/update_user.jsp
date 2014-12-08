<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Update User Page</title>
</head>
<body>
<h3>Update User Page</h3>
 
<form:form modelAttribute="user" method ="POST" commandName="user" action="./${user.id}">
 <table>
<tr>
	<td><form:label path="username">Name:</form:label></td>
	<td><form:input path="username"></form:input></td>
</tr>
<tr>
	<td><form:label path="password">Password:</form:label></td>
	<td><form:password path="password"></form:password></td>
</tr>
<tr>
	<td><input type="submit" value="Submit" /></td>
	<td><input type="reset" value="Reset"/></td>
</tr>
</table>

</form:form>
</body>
</html>