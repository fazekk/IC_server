<table align="center" border="1">
    <tr>
        <td>Nr:</td><td>Name:</td><td>Email</td><td>Modify?</td>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
            <td><c:out value="${status.count}"/></td><td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.email}"/></td><td>Modify</td>
        </tr>
    </c:forEach>
</table>