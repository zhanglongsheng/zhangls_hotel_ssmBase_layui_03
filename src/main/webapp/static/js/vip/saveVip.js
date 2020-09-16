layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate;  //引用日期模块

    var checkIdcardIf = false;  //判断手机号的唯一性全局变量

    var checkPhoneIf = false;  //判断手机号的唯一性全局变量

    //自定义验证
    form.verify({  //做表单提交时的验证
        checkIdcard: function(value, item){ //value：表单的值、item：表单的DOM对象
            checkIdcard(value);  //手机号的唯一性验证，发送ajax请求访问数据库
            if(!checkIdcardIf){
                return '该身份证号已被使用！！';
            }
        },
        checkPhome: function(value, item){ //value：表单的值、item：表单的DOM对象
            checkPhome(value);  //手机号的唯一性验证，发送ajax请求访问数据库
            if(!checkPhoneIf){
                return '该手机号已被使用！！';
            }
        }
    });

    //下拉框的监听
    form.on('select(vipRate)', function(data){
        var nowDateStr = getNowDate(new Date());//获取当前时间的字符串  格式：yyyy/MM/dd HH:mm:ss
        $("#createDate").val(nowDateStr);  //将当前数据字符串数据回填到隐藏域，一并做添加提交
        if(data.value=='0.8'){  //得到被选中的值
            //生成会员卡号
            $("#vipNum").val(dateReplace(nowDateStr)+"01");
        } else {
            $("#vipNum").val(dateReplace(nowDateStr)+"02");
        }
    });

    //监听提交按钮，执行添加
    form.on('submit(demo2)', function (data) {
        var saveJsonVip = data.field;  //重新将查询条件赋值
        saveVip(saveJsonVip);  //执行添加
        return false;  //阻止表单跳转提交
    });

    /*****************************自定义的函数**************************/
    //身份证号的唯一性验证
    function checkIdcard(idcard) {
        $.ajax({
            type:'POST',
            url:'vip/getCountByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            async:false,  //表示可以在ajax外部取得ajax中的数据
            data:{"idcard":idcard},
            success:function (data) {
                if(data==0){
                    //tipsMore: true允许多个吸附框的弹出
                    layer.tips('此身份证号可以使用','#idcard', {tips: [2,'green'],time:2000,tipsMore: true});
                    checkIdcardIf = true;
                }else {
                    layer.tips('此身份证号已被使用','#idcard', {tips: [2,'red'],time:2000,tipsMore: true});
                    checkIdcardIf = false;
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    //手机号的唯一性验证
    function checkPhome(phone) {
        $.ajax({
            type:'POST',
            url:'vip/getCountByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            async:false,  //表示可以在ajax外部取得ajax中的数据
            data:{"phone":phone},
            success:function (data) {
                if(data==0){
                    layer.tips('此手机号可以使用','#phone', {tips: [2,'green'],time:2000,tipsMore: true});
                    checkPhoneIf = true;
                }else {
                    layer.tips('此手机号已被使用','#phone', {tips: [2,'red'],time:2000,tipsMore: true});
                    checkPhoneIf = false;
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    //获取当前时间字符串     Date()   ---->  yyyy/MM/dd HH:mm:ss 格式的字符串
    function getNowDate(date) {
        var sign1 = "/";
        var sign2 = ":";
        var year = date.getFullYear() // 年
        var month = date.getMonth() + 1; // 月
        var day  = date.getDate(); // 日
        var hour = date.getHours(); // 时
        var minutes = date.getMinutes(); // 分
        var seconds = date.getSeconds() //秒
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (day >= 0 && day <= 9) {
            day = "0" + day;
        }
        if (hour >= 0 && hour <= 9) {
            hour = "0" + hour;
        }
        if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
        }
        var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds ;
        return currentdate;
    }

    //把 2019/01/01 12:12:12  -->  20190101121212
    function dateReplace(date) {
        date = date.replace("/","");
        date = date.replace("/","");
        date = date.replace(" ","");
        date = date.replace(":","");
        date = date.replace(":","");
        return date;
    }

    //会员添加
    function saveVip(saveJsonVip) {
        $.ajax({
            type:'POST',
            url:'vip/save',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:saveJsonVip,
            success:function (data) {
                if(data=='success'){
                    layer.msg("会员数据添加成功。。",{icon: 1,time:2000,anim: 4,shade:0.5});
                    //定时器，2s后跳转到会员信息显示页面
                    setTimeout('window.location="model/toShowVip"',2000);
                }else {
                    layer.msg("会员数据添加失败！！",{icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }
});