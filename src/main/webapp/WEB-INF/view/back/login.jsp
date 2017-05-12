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
    <title>登录页面</title>
    ${f:addCss("resources/css/login.css")}
    ${f:addJs("resources/js/back/login.js")}
</head>
<body>
<div class="top_div"></div>
<div class="out_div" id="login">
    <div style="width: 165px; height: 96px; position: absolute;">
        <div class="tou"></div>
        <div class="initial_left_hand" id="left_hand"></div>
        <div class="initial_right_hand" id="right_hand"></div>
    </div>
    <p style="padding: 30px 0px 10px; position: relative;">
        <span class="u_logo"></span>
        <input class="ipt" type="text" id="username" placeholder="请输入用户名或邮箱" required="required" value="">
    </p>
    <p style="position: relative;">
        <span class="p_logo"></span>
        <input class="ipt" id="password" type="password" placeholder="请输入密码" required="required" value="">
    </p>
    <div class="in_div">
        <p style="margin: -40px 35px -10px 0px; color: red" id="tips" hidden="hidden">账号或密码错误</p>
        <p style="margin: 0px 35px 20px 45px;"><span style="float: left;">
            <a style="color: rgb(204, 204, 204);" href="javascript:void(0)" onclick="showForget()">忘记密码</a></span>
            <a style="color: rgb(204, 204, 204);" href="javascript:void(0)" onclick="showApply()">申请帐号</a></span>
            <span style="float: right;"><a class="a_login" href="javascript:void(0)" onclick="login()">登录</a></span>
        </p>
    </div>
</div>

<div class="out_div" id="applyAccount" style="height: 250px;" hidden="hidden">

    <p style="padding: 30px 0px 10px; position: relative;">
        <span class="u_logo"></span>
        <input class="ipt" type="text" id="a_username" placeholder="请输入用户名或邮箱" required="required" value="">
    </p>
    <p style="position: relative;margin-bottom: 10px;">
        <span class="p_logo"></span>
        <input class="ipt" id="a_password" type="password" placeholder="请输入密码" required="required" value="">
    </p>
    <p style="position: relative;margin-bottom: 10px;">
        <span class="p_logo"></span>
        <input class="ipt" id="a_password_2" type="password" placeholder="请再次输入密码" required="required" value="">
    </p>
    <div class="in_div">
        <p style="margin: -40px 35px -10px 0px; color: red" id="a_tips" hidden="hidden">账号或密码错误</p>
        <p style="margin: 0px 35px 20px 45px;">
            <span style="float: right;"><a class="a_login" href="javascript:void(0)" onclick="createAccount()">确定</a></span>
        </p>
    </div>
</div>

<div class="out_div" id="forgetAccount" style="height: 250px;" hidden="hidden">

    <p style="padding: 30px 0px 10px; position: relative;">
        <span class="u_logo"></span>
        <input class="ipt" type="text" id="f_username" placeholder="请输入用户名或邮箱" required="required" value="">
    </p>
    <p style="position: relative;margin-bottom: 10px;">
        <span class="p_logo"></span>
        <input class="ipt" id="f_password" type="password" placeholder="请输入密码" required="required" value="">
    </p>
    <p style="position: relative;margin-bottom: 10px;">
        <span class="p_logo"></span>
        <input class="ipt" id="f_password_2" type="password" placeholder="请再次输入密码" required="required" value="">
    </p>
    <div class="in_div">
        <p style="margin: -40px 35px -10px 0px; color: red" id="f_tips" hidden="hidden">账号或密码错误</p>
        <p style="margin: 0px 35px 20px 45px;">
            <span style="float: right;"><a class="a_login" href="javascript:void(0)" onclick="getBackAccount()">确定</a></span>
        </p>
    </div>
</div>

</body>
</html>
