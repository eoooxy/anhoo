/**
 * Created by eoooxy on 2017/4/26.
 */

//登录上方的动态画面效果
$(function () {
    //得到焦点
    $("#password").focus(function () {
        $("#left_hand").animate({
            left: "150",
            top: " -38"
        }, {
            step: function () {
                if (parseInt($("#left_hand").css("left")) > 140) {
                    $("#left_hand").attr("class", "left_hand");
                }
            }
        }, 2000);
        $("#right_hand").animate({
            right: "-64",
            top: "-38px"
        }, {
            step: function () {
                if (parseInt($("#right_hand").css("right")) > -70) {
                    $("#right_hand").attr("class", "right_hand");
                }
            }
        }, 2000);
    });
    //失去焦点
    $("#password").blur(function () {
        $("#left_hand").attr("class", "initial_left_hand");
        $("#left_hand").attr("style", "left:100px;top:-12px;");
        $("#right_hand").attr("class", "initial_right_hand");
        $("#right_hand").attr("style", "right:-112px;top:-12px");
    });
});

//登录判断
function login() {
    var user = $("#username").val();
    var pwd = $("#password").val();
    var tips = document.getElementById("tips");
    if (eoooxy.isEmpty(user) || eoooxy.isEmpty(pwd)) {
        tips.innerText = "用户名或者密码不能为空！";
        $(tips).show();
        return false;
    } else {
        var data = {"userName": user, "passWord": pwd};
        eoooxy.ajax("post", "/back/isLogin", data, function (r) {
            if (!eoooxy.isEmpty(r) && r.code == "100") {
                console.log(r)
                location.href = "/back/index";
            } else {
                tips.innerText = "帐号或密码错误！";
                $(tips).show();
            }
        }, "json")
    }
}

//显示申请帐号界面
function showApply() {
    $("#login").hide();
    $("#forgetAccount").hide();
    $("#applyAccount").show();
}

//显示登录窗口界面
function showLogin() {
    $("#forgetAccount").hide();
    $("#applyAccount").hide();
    $("#login").show();
}

//显示找回密码界面
function showForget() {
    $("#login").hide();
    $("#applyAccount").hide();
    $("#forgetAccount").show();
}

//创建帐号
function createAccount() {
    var user = $("#a_username").val();
    var pwd = $("#a_password").val();
    var pwd2 = $("#a_password_2").val();
    var tips = document.getElementById("a_tips");
    if (eoooxy.isEmpty(user) || eoooxy.isEmpty(pwd) || eoooxy.isEmpty(pwd2)) {
        tips.innerText = "用户名或者密码不能为空！"
        $(tips).show();
        return false;
    }
    if (!eoooxy.isEqual(pwd, pwd2)) {
        tips.innerText = "两次输入的密码不一致！";
        $(tips).show();
        return false;
    }

    var data = {"userName": user, "passWord": pwd};
    eoooxy.ajax("post", "/back/apply", data, function (r) {
        if (!eoooxy.isEmpty(r) && r.code == '100') {
            console.log(r)
            tips.innerText = "注册成功，3秒后跳转到其他页面";
            $(tips).show();

            //等待3s 跳转到index页面
            setTimeout(function () {
                location.href = "/back/index";
            }, 3000)
        } else {
            tips.innerText = r.msg;
            $(tips).show();
        }
    }, "json")

}

//找回密码
function getBackAccount() {



}

//发送验证码
function getValidateCode(){

}