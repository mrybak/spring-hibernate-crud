<%@ include file="/WEB-INF/jsp/include.jsp"%>
<jsp:include page="pageHeader.jsp" flush="true"></jsp:include>
<spf:form modelAttribute="mappedModel">
<spf:errors path="*"/>
<table  cellpadding="20">
    <tr style="background-color:#c0c0c0">
        <td>ID</td>
        <td><c:choose><c:when test="${empty mappedModel.id}">[Assigned on save]</c:when><c:otherwise>${mappedModel.id}</c:otherwise></c:choose></td>
    </tr>
    <tr style="background-color:#c0c0c0">
        <td>Name</td>
        <td><spf:input path="name"/><spf:errors path="name" cssClass="errors"/></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td><input type="submit" name="submit" value="Save"/>&nbsp;<input type="submit" name="submit" value="Cancel"/></td>
    </tr>
</table>
</spf:form>
<jsp:include page="pageFooter.jsp" flush="true"></jsp:include>