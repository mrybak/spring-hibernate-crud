<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title>Manage Departments</title>
<link rel="stylesheet" href="<c:url value='/css/departments.css'/>"
	type="text/css">
<script type="text/javascript">
function confirmDelete(departmentId) {
    var answer = confirm("Delete this department?");
    if(answer) {
        document.location.href = "<c:url value='/deleteDepartment.html'/>?id=" + departmentId;
    }
}
</script>
</head>
<body>
	<h1>Manage Departments</h1>
	<hr>
	<table width="60%">
		<c:choose>
			<c:when test="${empty departments}">
				<tr>
					<td colspan="4">No departments were found.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th align="left">Functions</th>
				</tr>
				<c:forEach items="${departments}" var="department"
					varStatus="status">
					<tr class="row${status.index % 2 }">
						<td align="center">${department.id}</td>
						<td align="center">${department.name}</td>
						<td><a
							href="<c:url value='/editDepartment.html'><c:param name="id" value="${department.id}"/><c:param name="action" value="edit"/></c:url>">Edit</a>&nbsp;<a
							href="javascript:void(0);"
							onclick="confirmDelete('${department.id}');">Delete</a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr style="margin-top: 20px;">
			<td>&nbsp;</td>
			<td colspan="2"><button
					onclick="document.location.href='<c:url value='/editDepartment.html'><c:param name='action' value='edit'/></c:url>';">Add
					new department</button></td>
		</tr>
		<tr style="margin-top: 20px">
			<td>&nbsp;</td>
			<td><button
					onclick="document.location.href='<c:url value='/index.html'/>';">Home</button></td>
		</tr>
	</table>
</body>
</html>