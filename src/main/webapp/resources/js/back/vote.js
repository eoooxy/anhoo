/**
 * Created by eoooxy on 2017/5/23.
 */


function vId() {
    var id = $("#inviteCode").val();
    var reg = /\d{8}/;
    if (eoooxy.isEmpty(id) || !reg.test(id) || id.length > 8) {
        alert("请输入8位邀请码")
        return false;
    }
    var label = $("#sInviteCode");
    label.html(id);

}

function addNum(key) {
    var id = $("#inviteCode").val();

    if (eoooxy.isEmpty(id)) {
        alert("请输入8位邀请码")
        return false;
    }
    var data = {"inviteCode": id, "key": key}
    eoooxy.ajax("post", "/back/add", data, function (r) {
        if (!eoooxy.isEmpty(r) && r.code == '100') {
            alert(r.msg);
            setTimeout(function () {
                location.href = "/back/vote";
            }, 1000)
        } else {
            alert(r.msg);
        }
    });
}