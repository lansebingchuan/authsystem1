function initIndexSysTemDate() {
    $.post("/product/listAllProduct", function (data) {
        if (data.flage){
            var sysReglist = data.productList;
            var sysDiv = $("#sysDiv");
            $(sysReglist).each(function (index, product) {
                var sysDivLi ='<div class="col-sm-6 col-md-4 col-lg-3">\n' +
                    '            <div class="row">\n' +
                    '                <div class="col-sm-12" style="height: 200px">\n' +
                    '                    <img width="100%" height="100%" src="' +
                    product.icon+'" alt="' +
                    product.depict+'">\n' +
                    '                </div>\n' +
                    '                <div class="col-sm-12 title">\n' +
                    product.depict +
                    '                </div>\n' +
                    '                <div class="col-sm-12 collection">\n' +
                    '                    评价64&nbsp;&nbsp;收藏444\n' +
                    '                </div>\n' +
                    '                <div class="col-sm-12 ">\n' +
                    '                    <div class="pull-left price">\n' +
                    '                        ¥' +
                    product.price+
                    '                    </div>\n' +
                    '                    <div class="pull-right sell">\n' +
                    '                        月销' +
                    product.buyCount +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '                <div class="col-sm-12">\n' +
                    '                    <div class="pull-right">\n' +
                    '                        <i class="glyphicon glyphicon-shopping-cart cart" productId="' +
                    product.id+'" title="加入购物车"></i>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>';
                sysDiv.append(sysDivLi);
            })
            $(".cart").click(function () {
                var productId = $(this).attr("productId")
                layer.confirm("确认加入购物车吗？", function () {
                    $.post("/main/addProduct", {productId: productId}, function (data) {
                        if (data.flage){
                            layer.msg("加入购物车成功！")
                        }else {
                            if (data.user === 'n'){
                                layer.msg("请登录后添加！", {icon: 6})
                            }else {
                                layer.msg("加入购物车失败！", {icon: 5})
                            }

                        }
                    })
                })
            })
        }else{
            layer.msg("数据请求失败！")
        }
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
            axios.get('/main/getSessionUser',
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

