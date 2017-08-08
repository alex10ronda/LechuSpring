<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
	<spring:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" var="bootstrap" />
	<spring:url value="/resources/bower_components/jquery/dist/jquery.min.js" var="jqueryJs" />
	
	<link href="${bootstrap}" rel="stylesheet" />
	<script src="${jqueryJs}"></script>
	
</head>
<body>
<h2>Hello World!</h2>
<form:form method="POST" action="/LechuSpring/setTp" modelAttribute="TpCartaForm">
	<form:select path="tpCarta">
		<form:option value="2">2</form:option>
		<form:option value="3">3</form:option>
		<form:option value="4">4</form:option>
		<form:option value="5">5</form:option>
	</form:select>
	
	<form:button>OK</form:button>
</form:form>

</body>
</html>
