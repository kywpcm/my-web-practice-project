<%--
  Created by IntelliJ IDEA.
  User: kywpcm
  Date: 2018. 7. 11.
  Time: PM 2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
[1] 현재 페이지의 세션 ID 입니다. : [ <%= session.getId() %> ]
<br><hr><br>
[2] 세션 값이 저장되어 있는지 체크합니다. : [ <%= session.getAttribute("value") %> ]
<br><hr><br>
[3] 이번엔 세션 ID가 쿠키로 왔는지 URL로 왔는지 체크합니다.
<br><br>
<%
    if (request.isRequestedSessionIdValid())
        out.println("세션 ID가 쿠키에 실려왔습니다.");
%>
<br><hr><br>
</body>
</html>
