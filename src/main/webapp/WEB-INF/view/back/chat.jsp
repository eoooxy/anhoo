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
    ${f:addJs("resources/js/back/chat.js")}
</head>
<body>
<div style="width: auto;" align="center">
    <h1>多人聊天室</h1>
    <span>
        <input id="user" style="resize: none;outline: none;" placeholder="昵称，必须输入">
        <button onclick="chatting()">开始聊天</button>
    </span>
    <div id="chatSpace"
         style="width: 600px;height: 500px; border: solid 1px #CCCCCC; overflow-y: auto;text-align: left">

    </div>
    <div style="width: 600px; height: 100px;border: solid 1px #CCCCCC; margin-top: 20px;">
        <textarea id="content"
                  style="width: 590px;height: 60px; resize: none;border: 0px;outline: none;margin: 5px;"></textarea>
        <button style="float: right;margin-right: 10px;" id="btn_send" onclick="sendInfo()">确定</button>
    </div>
</div>
</body>
</html>
