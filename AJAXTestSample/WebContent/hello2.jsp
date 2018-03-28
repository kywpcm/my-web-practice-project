<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">
	
	// AJAX�� �񵿱� JavaScript�� XML�� ���մϴ�.
	// ������ ���ϸ�, ������ Scripts�� ����ϱ� ���� XMLHttpRequest��ü�� ����ϴ� ���� ���մϴ�.
	
	window.onload = function() {

		var httpRequest;
		document.getElementById("send").onclick = function() {
			makeRequest('http://localhost:8080/AJAX/send_alias.do');
		};

		function makeRequest(url) {

			console.log('�α� �׽�Ʈ');

			if (window.XMLHttpRequest) { // Mozilla, Safari, ...
				httpRequest = new XMLHttpRequest();
			} else if (window.ActiveXObject) { // IE
				try {
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
					}
				}
			}

			if (!httpRequest) {
				alert('Giving up :( Cannot create an XMLHTTP instance');
				return false;
			}

			url = url + "?inputNum=" + document.getElementById("inputNum").value;

			httpRequest.onreadystatechange = alertContents;
			httpRequest.open('GET', url);
			httpRequest.send(null);
		}
		
		function alertContents() {
			if (httpRequest.readyState === 4) {
				if (httpRequest.status === 200) {
					document.getElementById("outputNum").value = httpRequest.responseText;
				} else {
					alert('There was a problem with the request.');
				}
			}
		}
	};
</script>
</head>

<body>
	<p>hello world~!!!</p>
	<br>

	<p>���� �׽�Ʈ</p>
	<form>
		<input type="text" id="inputNum">
		<input type="button" id="send" value="SEND">
		<br>
		<input type="text" id="outputNum">
	</form>
</body>

</html>
