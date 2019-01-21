<%--
  Created by IntelliJ IDEA.
  User: kywpcm
  Date: 2018. 2. 13.
  Time: PM 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String url = "new_page.jsp";
    String value = "권영우 세션";
    session.setAttribute("value", value);
    String ls_session_id = session.getId();
%>

<html>
<head></head>
<body>
다음은 쿠키에 정보를 포함하는 방법에 대한 예제입니다. :
[ <%= url %> ]<br>
<hr>
[1] 현재 세션 ID: <%= ls_session_id %>입니다.<br>
<hr>
<!-- 그냥 URL로 링크를 걸어주면 세션 단위의 쿠키에 저장된다. -->

[2]
<a href="<%= url%>">
    여기를 누르면
</a>
<br><br>
세션 ID를 URL에 포함하지 않고 쿠키에 저장하여 새로운 페이지로 이동한다.
<hr>
</body>
</html>
