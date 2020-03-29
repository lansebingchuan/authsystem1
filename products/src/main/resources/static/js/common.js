function openCenterWin(title, nextText, url , callback,data){

    layui.use('layer', function(){
        window.top.layer.open({
            type: 2,
            title:title,
            closeBtn: 2,
            area: ['800px', '550px'],
            fix: true, //不固定
            maxmin: false,
            zIndex:1010,
            content: [url,'yes'],
            btn: [nextText,'取消'],
            move: false,
            success: function(layero, index){
                var iframeWin = window.top.window[layero.find('iframe')[0]['name']];
                if (data){
                    iframeWin.setData(data);
                }
            },
            yes: function(index, layero){
                var iframeWin = window.top.window[layero.find('iframe')[0]['name']];
                var data = iframeWin.submitForm();
                if(data){
                    window.top.layer.close(index);
                    if (callback) {
                        callback(index , data);
                    }
                }
            },
            cancel: function(){},
        });
    })
}

/* 下拉框选项全部选中 */
function selected(id) {
    var selectobj = document.getElementById(id);
    for ( var i = 0; i < selectobj.options.length; i++) {
        selectobj.options[i].selected = true;
    }
}
/*获取URL路径的值 --如： id*/
function getUrlId(id){
    var reg = new RegExp("(^|&)"+ id +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]);
    return null;
}