<%--
  Created by IntelliJ IDEA.
  User: Youngwoo Max Kwon
  Date: 2018-09-17
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>result page</title>
    <script>
        function request01() {
            location.href = "RequestProcServlet01.do";
        }
        function request02() {
            location.href = "RequestProcServlet02.do";
        }
        function request03() {
            location.href = "main.jsp";
        }
    </script>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String passwd = request.getParameter("pw");

    if ("kywpcm".equals(id)) {
        session.setAttribute("id", id);
        session.setAttribute("name", "권영우");
        session.setAttribute("age", 33);
%>
<p>로그인 성공</p>
<p>- 세션 저장</p>
<div><input type="button" id="request01" value="request01" onclick="request01()"></div>
<div><input type="button" id="request02" value="request02" onclick="request02()"></div>
<div><input type="button" id="request03" value="request03" onclick="request03()"></div>
<%
    } else {
%>
<p>로그인 실패</p>
<%
    }
%>
</body>
</html>
