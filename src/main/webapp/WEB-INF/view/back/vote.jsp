<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/19
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    ${f:addJs("resources/js/back/vote.js")}
    <%@include file="/WEB-INF/view/common/meta.jsp" %>
    <title>redis投票实例</title>
</head>
<body>
<div style="width: auto;margin-top: 50px;" align="center">
    <div>
        <span>输入邀请码：<input id="inviteCode" placeholder="八位邀请码"><button id="ok" onclick="vId()">确定</button></span><br/>
        <span>当前输入邀请码为：<label id="sInviteCode"></label></span>
    </div>

    <table style="margin-top: 20px;" id="table">
        <tr>
            <th>姓名</th>
            <th>票数</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${votes}" var="v">
            <tr>
                <td>${v.value}</td>
                <td><fmt:formatNumber type="number" minFractionDigits="0" value="${v.score}"/></td>
                <td>
                    <button onclick="addNum('${v.value}')">投票</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
