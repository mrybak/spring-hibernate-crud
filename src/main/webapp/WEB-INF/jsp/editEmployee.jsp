<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="pageHeader.jsp" flush="true"></jsp:include>
<spf:form modelAttribute="mappedModel">
<spf:errors path="*"/>
<table  cellpadding="20">
    <tr style="background-color:#c0c0c0">
        <td>ID</td>
        <td><c:choose><c:when test="${empty mappedModel.id}">[Assigned on save]</c:when><c:otherwise>${mappedModel.id}</c:otherwise></c:choose></td>
    </tr>
    <tr style="background-color:#c0c0c0">
        <td>First Name</td>
        <td><spf:input path="firstName"/><spf:errors path="firstName"/></td>
    </tr>
    <tr style="background-color:#c0c0c0">
        <td>Last Name</td>
        <td><spf:input path="lastName"/><spf:errors path="lastName"/></td>
    </tr>
    <tr>
        <td valign="top">Available Departments</td>
        <td>
            <c:forEach items="${allDepartments}" var="department" varStatus="status">
                <c:set var="hasDept" value="false"/>
                <c:forEach items="${mappedModel.departments}" var="empDept">
                    <c:if test="${empDept.id == department.id}">
                        <c:set var="hasDept" value="true"/>
                    </c:if>
                </c:forEach>
                <input id="departments${status.index+1}" name="departments" value="${department.id}" type="checkbox" <c:if test="${hasDept}">CHECKED</c:if>/><label for="departments${status.index+1}">${department.name}</label><br/>
            </c:forEach>
            <input  type="hidden" value="_on" name="_departments"/>
        </td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td><input type="submit" name="submit" value="Save"/>&nbsp;<input type="submit" name="submit" value="Cancel"/></td>
    </tr>
</table>
</spf:form>
<jsp:include page="pageFooter.jsp" flush="true"></jsp:include>