<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${empty mappedModel.id}">
		<c:set var="pageTitle">Add ${mappedModelName}</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="pageTitle">Edit ${mappedModelName}</c:set>
	</c:otherwise>
</c:choose>
<html>
<head>
<title>${pageTitle}</title>
<link rel="stylesheet" href="<c:url value='/css/departments.css'/>"
	type="text/css">
</head>
<body>
	<h1>${pageTitle}</h1>
	<hr>