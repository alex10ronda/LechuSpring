<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<html>
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
