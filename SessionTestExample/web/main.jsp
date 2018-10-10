<%--
  Created by IntelliJ IDEA.
  User: Youngwoo Max Kwon
  Date: 2018-09-17
  Time: 오후 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main page 03</title>
</head>
<body>
    <p>id : <%=session.getAttribute("id")%></p>
    <p>name : <%=session.getAttribute("name")%></p>
    <p>age : <%=session.getAttribute("age")%></p>
</body>
</html>
