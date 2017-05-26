<%--
  Created by IntelliJ IDEA.
  User: eoooxy
  Date: 2017/4/6
  Time: 下午9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/view/common/meta.jsp" %>
    <title>功能页面</title>
    ${f:addJs("resources/js/back/index.js")}
</head>
<body>
<h1>hello world</h1>
<ul>
    <li><a href="/back/vote">redis投票实例</a></li>
    <li><a href="/back/chat">redis多人聊天室</a></li>
</ul>
</body>
</html>
