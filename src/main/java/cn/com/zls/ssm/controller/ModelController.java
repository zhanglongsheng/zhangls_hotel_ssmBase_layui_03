package cn.com.zls.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/model")
public class ModelController {

@RequestMapping("/toIndex")
    public String toIndex(){

        return "index" ;
    }
@RequestMapping("/toShowInRoomInfo")
    public String toShowInRoomInfo(){
    return "inRoomInfo/showInRoomInfo";
    }

    //去到入住信息添加页面
    @RequestMapping("/toSaveInRoonInfo")
    public String toSaveInRoonInfo(){
        return "inRoomInfo/saveInRoomInfo";
    }
    //去到订单显示页面
    @RequestMapping("/toShowOrders")
    public String toShowOrders(){
        return "orders/showOrders";
    }
    @RequestMapping("/toOrdersPay")
    public String toOrdersPay(){

        return "alipay/ordersPay";
    }
    //支付回调失败去到异常页面
    @RequestMapping("/toErrorPay")
    public String toErrorPay(){
        return "errorPay";
    }
    //去到消费记录页面
    @RequestMapping("/toShowRoomSale")
    public  String toShowRoomSale(){
    return "roomSale/showRoomSale";
    }
   //去到会员信息查询页面
    @RequestMapping("/toShowVip")
    public String toShowVip(){

    return "vip/showVip" ;
    }
    //去到会员添加页面
    @RequestMapping("/toSaveVip")
    public String toSaveVip(){
    return "vip/saveVip" ;
    }
    //去到客房显示页面
    @RequestMapping("/toShowRooms")
    public String toShowRooms(){
    return "rooms/showRooms";
    }

    //去到房型显示页面
    @RequestMapping("/toShowRoomType")
    public String toShowRoomType(){
        return "roomType/showRoomType";
    }

    //去到登陆页面
    @RequestMapping("/loginUI")
    public String loginUI(){
        return "login/login";
    }

    //去到销售信息查询界面
    @RequestMapping("/toShowDbi")
    public String toShowDbi(){
        return "dbi/showdbi";
    }
}
