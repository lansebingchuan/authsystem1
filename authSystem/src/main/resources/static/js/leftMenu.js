var menuTree = $("#menu_tree")
$.ajaxSettings.async = false;//设置为同步
$.post("/getMenuTree", {sysUuid: 'c9a273cc-9263-4835-b0f4-7c7725b921b3'}, function (data) {
    debugger
    if (data == 'no'){
        window.top.layer.alert('登录超时！', {icon: 4, title: '提示'});
        return
    }
    var tree = $("#menu_tree");
    tree.html($(data))
}).error(function () {
    window.top.layer.msg("系统错误！", { time: 1500, icon: 5, shift: 6 }, function () {
    });
})
$.ajaxSettings.async = true;//设置为同步