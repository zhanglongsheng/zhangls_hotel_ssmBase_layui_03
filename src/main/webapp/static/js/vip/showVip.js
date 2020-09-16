layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate;  //引用日期模块

    var checkPhoneIf = false;  //判断手机号的唯一性全局变量

      var selJsonVip = {} ;
      //加载会员信息
      loadPageVip()
    
    function loadPageVip() {
        table.render({  //数据表格的数据渲染(此UI框架底层是进行异步加载)
            elem: '#demo'  //绑定容器  根据标签（数据容器）的id属性来
            , height: 412   //容器高度
            , limit: 3   //每一页显示的数据条数，默认值为10
            , limits: [2, 3, 5, 8, 10, 15, 20]   //进行每一页数据条数的选择
            ,where:selJsonVip
            , url: 'vip/loadPageByPramas' //访问服务器端的数据接口(异步请求)，返回的json格式的数据
            , page: true //开启分页,此时会自动的将当前页page和每一页数据条数limit的数值传回服务器端
            , cols: [[ //表头
                //加入复选框列
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
                , {field: 'vipNum', title: '会员卡号', align: 'center', width: 140, sort: true}
                , {field: 'customerName', title: '客人姓名', align: 'center', width: 140, sort: true,edit:'text'}
                , {field: 'vipRate', title: '会员类型', align: 'center', width: 210,templet: '#vipRateTpl'}
                , {field: 'gender', title: '性别', align: 'center', width: 210,templet: '#genderTpl'}
                , {field: 'idcard', title: '身份证号', align: 'center', width: 120, sort: true}
                , {field: 'phone', title: '手机号', align: 'center', width: 120, sort: true}
                , {field: 'createDate', title: '创建时间',align: 'center', width: 120, sort: true}
                , {title: '操作', align: 'center', toolbar: '#barDemo',fixed:'right', width: 160}
            ]],
            done: function (res, curr, count) {  //执行分页是的函数回调；res为分页时服务器端的整个Map集合数据  curr为当前页  count为总的数据条数

            }
        });
    }
    //根据条件查询消费记录数据,提交监听表单
    form.on('submit(demo1)', function (data) {
        selJsonVip = data.field; //重新将查询条件赋值
        loadPageVip(selJsonVip);
        return false; //阻止表单跳转提交
    });

    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）根据获取的值判断执行编辑或者删除操作
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'query'){ //查询

        } else if(layEvent === 'upd') { //退房
            $("#vip_id").val(data.id);  //回显id，提交表单时一并提交做修改
            $("#phone").val(data.phone);  //回显手机号
            var selVipRateOptStr = '';
            if(data.vipRate=='0.9'){
                selVipRateOptStr = '<option value="0.9" selected>普通会员</option><option value="0.8">超级会员</option>';
            }else {
                selVipRateOptStr = '<option value="0.8" selected>超级会员</option><option value="0.9">普通会员</option>';
            }
            $("#vipRate").html(selVipRateOptStr);
            form.render("select");//渲染下拉框
            layer.open({
                type:1,  //弹出类型
                title:"退房操作界面",  //弹框标题
                area:['500px','400px'],  //弹框款高度
                anim: 4,  //弹出的动画效果
                shade:0.5,  //阴影遮罩
                content:$("#updVipDiv")  //弹出的内容
            });
            //3.执行修改
            //根据条件查询消费记录数据,提交监听表单
            form.on('submit(demo3)', function (data) {
                updVip(data.field,obj);
                layer.closeAll();  //关闭所有弹框
                return false;  //阻止表单提交
            });
        }
        //自定义验证
        form.verify({  //做表单提交时的验证
            checkPhome: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(data.phone!=value){  //判断用户有进行手机号的修改，修改进行判断，不修改就不判断
                    checkPhome(value);  //手机号的唯一性验证，发送ajax请求访问数据库
                    if(!checkPhoneIf){
                        return '该手机号已被使用！！';
                    }
                }
            }
        });
    });

    //监听单元格编辑（只有当单元格数据发生变化才会触发此监听）
    table.on('edit(test)', function(obj){
        var value = obj.value //得到修改后的值
            ,data = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        //执行修改
        var updJsonVip = {};
        updJsonVip['id'] = data.id;
        updJsonVip[field] = value;  //修改的字段数据
        //执行修改
        updVipCustomerName(updJsonVip);
    });
    /***********************自定义函数*******************************/

    //手机号的唯一性验证
    function checkPhome(phone) {
        $.ajax({
            type:'POST',
            url:'vip/getCountByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            async:false,  //表示可以在ajax外部取得ajax中的数据
            data:{"phone":phone},
            success:function (data) {
                if(data==0){
                    checkPhoneIf = true;
                }else {
                    checkPhoneIf = false;
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    //修改会员数据
    function updVip(updJsonVip,obj) {
        $.ajax({
            type:'POST',
            url:'vip/updByPrimaryKeySelective',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:updJsonVip,
            success:function (data) {
                if(data=='success'){
                    layer.msg("会员数据修改成功。。",{icon: 1,time:2000,anim: 4,shade:0.5});
                    obj.update({  //更新页面缓存  （若是单表修改并且不修改关联表的字段，则用此方法，否则直接重新加载表格当前页数据）
                        phone:updJsonVip.phone,
                        vipRate:updJsonVip.vipRate
                    });
                }else {
                    layer.msg("会员数据修改失败！！！",{icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }
    //修改会员姓名
    function  updVipCustomerName(updJsonVip) {
        $.ajax({
            type:'POST',
            url:'vip/updByPrimaryKeySelective',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:updJsonVip,
            success:function (data) {
                if(data=='success'){
                    layer.msg("会员姓名修改成功。。",{icon: 1,time:2000,anim: 4,shade:0.5});
                }else {
                    layer.msg("会员姓名修改失败！！！",{icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }
});