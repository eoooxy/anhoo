/**
 * Created by eoooxy on 2017/4/26.
 */
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

function login() {
    var user = $("#username").val();
    var pwd = $("#password").val();
    if (eoooxy.isEmpty(user) || eoooxy.isEmpty(pwd)) {
        var tips = $("#tips").innerText = "用户名或者密码不能为空！";
        tips.show();
        return false;
    } else {
        var data = {"userName": user, "passWord": pwd};
        eoooxy.ajax("post", "/back/isLogin", data, function (r) {
            if (!eoooxy.isEmpty(r)) {
                console.log(r)
            }
            // location.href = "/back/index";
        })
    }
}