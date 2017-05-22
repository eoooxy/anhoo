<%--
  Created by IntelliJ IDEA.
  User: eoooxy
  Date: 2017/3/26
  Time: 下午4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/view/common/meta.jsp" %>
    <title>聊天室</title>
</head>
<body>
<h1>多人聊天室</h1>
<div>
    <input id="user"><br/>
    <input id="content">
    <button id="btn_send" onclick="sendInfo()">确定</button>
</div>
</body>
<script>
    function sendInfo() {
        var data = {"user": $("#user").val(), "content": $("#content").val()};
        eoooxy.ajax("post", "/back/send", data, function (r) {

        })
    }

</script>
</html>
