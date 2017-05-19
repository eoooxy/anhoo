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
    <%@include file="/WEB-INF/view/common/meta.jsp" %>
    <title>redis投票实例</title>
</head>
<body>
<div>
    <form id="form">
        <span>输入邀请码：<input id="inviteCode" placeholder="八位邀请码"><button id="ok" onclick="vId()">确定</button></span><br/>
        <span>当前输入邀请码为：<label id="sInviteCode"></label></span>
    </form>
</div>

<table style="margin-top: 20px;">
    <tr>
        <th>姓名</th>
        <th>票数</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${votes}" var="v">
        <tr>
            <td>${v.name}</td>
            <td>${v.scores}</td>
            <td>
                <button onclick="addNum('${v.name}')">投票</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

<script>
    function vId() {
        var id = $("#inviteCode").val();
        var f = $("#form");
        var reg = /\d{8}/;
        if (eoooxy.isEmpty(id) || !reg.test(id) || id.length > 8) {
            alert("请输入8位邀请码")
            return false;
        }
        var label = $("#sInviteCode");
        label.html(id);
        f.attr("action","/voteList").submit();


//        eoooxy.ajax("post", "/voteList", null, function (r) {
//            if (eoooxy.isEmpty(r) && r.code == 100) {
//                window.location.href = "/voteList";
//            }
//        });
    }

    function addNum(str) {

    }
</script>
</html>
