var topMain = new Vue({//top Vue元素
    el: "#top",
    data: {
        userName: "请登录"
    },
});
$(function(){//获取用户名称
    $.get("/getSessionUser" , function (result) {
        if (result.user) {
            topMain.userName = result.user.userName;
        }else {
            topMain.userName = '请登录';
            layer.open({
                title: '提示信息'
                ,content: '登录超时，请重新登录！',
                yes: function(index, layero){
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                    window.location.href="/login"
                }
            });
        }

    });
});


function showDate() {//显示日期变化
    var now = new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth() + 1;//得到月份
    var date = now.getDate();//得到日期
    // var day = now.getDay();//得到周几
    var hour = now.getHours();//得到小时数
    var minute = now.getMinutes();//得到分钟数
    var second = now.getSeconds();//得到秒数
    var yearText = year + "-" + month + "-" + date + "  " + hour + ":" + minute + ":" + second
    document.getElementById("yearText").innerHTML = yearText;
    setTimeout(showDate, 1000);//定时器一直调用show()函数
    return "";
}

function exit() {
    window.location.href = "/";
}
