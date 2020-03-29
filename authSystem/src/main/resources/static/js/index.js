function initIndexSysTemDate() {
    $.post("/index/initIndexSysTemDate", {}, function (result) {
        var sysReglist = result;
        var sysDiv = $("#sysDiv");
        $(sysReglist).each(function (index, sysReg) {
            var sysDivLi =
                '<div class="col-sm-6 col-md-4 col-lg-3 ">\n' +
                '            <div class="thumbnail" style="height: 336px;">\n' +
                '                <a href="#" title="'+sysReg.sysName+'"  onclick="openSysUrl(\''+sysReg.sysUrl+'\', \''+ sysReg.sysUuid +'\')" style="display: block;height: 126px">\n' +
                '                    <img class="lazy" style="height: 126px" src="'+sysReg.sysIconPath+'" width="300px" height="150px" data-src="" alt='+sysReg.sysDescribed+'>\n' +
                '                </a>\n' +
                '                <div class="caption">\n' +
                '                    <h3>\n' +
                sysReg.sysName+'<br><small>'+sysReg.sysTypeName+'</small>\n' +
                '                    </h3>\n' +
                '                    <p>'+sysReg.sysDescribed+'</p>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n'
            sysDiv.append(sysDivLi);
        })

    })
}

var loginMessage = new Vue({
    el: "#bs-navbar",
    data: {
        flage: false,
        user: {},
        count: 3,
    },
    methods: {
        permissions: function () {//是否具有管理员权限
            axios.get('/getSessionUser',
                {
                    params: {//参数对象
                        // cardNo: 12345, cardBinCheck: true,
                    },
                    responseType: "json",//设置返回类型 stream-图片
                }
            ).then(function (response) {
                var data = response.data;
                if (data.flage){
                    loginMessage.user = data.user;
                }
                loginMessage.flage = data.flage;
            })//response包含了头信息，与地址信息，response.data是服务器返回的数据
                .catch(function (error) { // 请求失败处理
                    // alert('出错了')
                    console.log(error);
                });
        },
    },
    watch: {//表示监听器，监听某个值是否改变了，那么就做事情
    },
});
loginMessage.permissions();


function openSysUrl(sysUrl, sysUuid) {

    //判断普通用户是否注册了这个系统
    $.get("/flagAverageuserReg", { sysUuid: sysUuid} , function (data) {
        if (data === 0){
            layer.msg("等待审核中，暂时不能使用！", {icon: 6})
        }else if(data === 1){
            window.open(sysUrl);
        }else if(data === 2){
            layer.msg("审核失败，不能使用该系统！", {icon: 5})
        }else if(data === 'noUser'){
            layer.msg("请登录后使用！", {icon: 5})
        }else {
            layer.msg("你还没注册该系统，请注册后使用！", {icon: 5})
        }
    })

}