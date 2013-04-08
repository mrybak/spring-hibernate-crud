<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<title>Manage Employees</title>
<link rel="stylesheet" href="<c:url value='/css/departments.css'/>" type="text/css" >
<script type="text/javascript">
function confirmDelete(employeeId) {
    var answer = confirm("Delete this employee?");
    if(answer) {
        document.location.href="<c:url value='/deleteEmployee.html'/>?id=" + employeeId;
    }
}
 
</script>
</head>
<body>
<h1>Manage Employees</h1>
<hr>
<table width="60%">
<c:choose>
<c:when test="${empty employees}">
    <tr>
        <td colspan="5">No employees were found</td>
    </tr>
</c:when>
<c:otherwise>
    <tr class="tableheader">
        <th>Employee ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Departments</th>
        <th>Functions</th>
    </tr>
    <c:forEach items="${employees}" var="employee" varStatus="status">
        <tr class="row${status.index % 2 }">
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>
            <c:choose>
                <c:when test="${empty employee.departments}">
                [None]
                </c:when>
                <c:otherwise>
                    <c:forEach items="${employee.departments}" var="department">
                    ${department.name}<br/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </td>
            <td><a href="<c:url value='/editEmployee.html'><c:param name='id' value='${employee.id}'/><c:param name='action' value='edit'/></c:url>">Edit</a>  <a href="javascript:void(0);" onclick="confirmDelete('${employee.id}');">Delete</a></td>
        </tr>
    </c:forEach>
</c:otherwise>
</c:choose>
<tr style="margin-top:20px;">
    <td>&nbsp;</td>
    <td colspan="2"><button onclick="document.location.href='<c:url value='/editEmployee.html'><c:param name='action' value='edit'/></c:url>';">Add new employee</button></td>
</tr>
<tr style="margin-top:20px">
    <td>&nbsp;</td>
    <td><button onclick="document.location.href='<c:url value='/index.html'/>';">Home</button></td>
</tr>
</table>
</body>
</html>