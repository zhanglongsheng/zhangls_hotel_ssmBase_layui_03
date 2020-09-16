package cn.com.zls.ssm.controller;

import cn.com.zls.ssm.entity.Rooms;
import cn.com.zls.ssm.utils.QiniuUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 *   客房控制器层
 */
@Controller
@RequestMapping("/rooms")
public class RoomsController extends BaseController<Rooms> {
//
//    //客房封面图上传
//    @RequestMapping("/uploadRoomPic")
//    public @ResponseBody
//    Map<String,Object> uploadRoomPic(String path, MultipartFile myFile){
//        return FileUploadUtils.upload(path,myFile);  //调用工具类执行上传
//    }


    //客房封面图上传
    @RequestMapping("/uploadRoomPic")
    public @ResponseBody Map<String,Object> uploadRoomPic(MultipartFile myFile){
        return QiniuUploadUtils.upload(myFile); //调用七牛云工具类执行上传
    }
}
