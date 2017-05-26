/**
 * Created by eoooxy on 2017/5/23.
 */
$(function () {
    $("#user").focus();
});

function loading() {
    eoooxy.ajax("post", "/back/callBack", null, function (r) {
        if (!eoooxy.isEmpty(r) && r.code == '100') {
            var o = r.content;
            var h = "<div style='margin: 10px 20px 10px 20px;'><label>" + o.user + "</label><br><label>" + o.content + "</label></div>";
            $("#chatSpace").append(h);
            loading();
        } else {
            loading();
        }
    }, "json")
}

function chatting() {
    if (eoooxy.isEmpty($("#user").val())) {
        alert("必须先输入昵称，然后点击开始聊天！");
        return false;
    }
    $("#user").attr("disabled", "disabled");
    var data = {"user": $("#user").val()};
    eoooxy.ajax("post", "/back/join", data, function (r) {
        if (!eoooxy.isEmpty(r) && r.code == '100') {
            loading();
        } else {
            alert(r.msg);
        }
    }, "json")
}

function sendInfo() {
    var message = $("#content");
    var data = {"user": $("#user").val(), "content": $("#content").val()};
    // var data = {"user": "jack", "content": $("#content").val()};
    eoooxy.ajax("post", "/back/send", data, function (r) {
        if (!eoooxy.isEmpty(r) && r.code == '100') {
            message.val('');
            $("#chatSpace")[0].scrollTop = $("#chatSpace")[0].scrollHeight;
            message.focus();
        } else {
            alert(r.msg);
        }

    })
}