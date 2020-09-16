package cn.com.zls.ssm.utils;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QiniuUploadUtils {

    //设置好账号的ACCESS_KEY和SECRET_KEY;这两个登录七牛账号里面可以找到 
    static String ACCESS_KEY = "0Py9tNA1Jzi-yFSad3d1iPAbqnmy3fxUV0x4se_v";
    static String SECRET_KEY = "Qj2xdUF1FAZSTGWI2Zob7CwbEm8oz8goRrHNU_U0";
    //要上传的空间;对应到七牛上（自己建文件夹 注意设置公开）  
    static String bucketname ="zhangls";
    //上传文件的路径 ;本地要上传文件路径  
  //  String FilePath = "C:\\Users\\Administrator\\Pictures\\good\\g1002\\g100201.jpg";
    //七牛云服务器存储空间的域名
    static String yuName ="http://qgacnb4qh.hn-bkt.clouddn.com/";
    //密钥配置  
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象  Zone.zone2()表示为华南地区   0-4  华东  华北  华南  北美  东南亚
    static UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了  
    public static String getUpToken(){
        return auth.uploadToken(bucketname);  
    }  
  
    //普通上传
    public static Map<String,Object> upload(MultipartFile myFile){
        //新建map集合
        Map<String,Object> map = new HashMap<String, Object>();
      try {
          //上传到七牛后保存的文件名（每一次上传让其重新加载，得到不同的目标文件名）
          String key = UUID.randomUUID().toString().replace("-", "");
        //调用put方法上传  
        Response res = uploadManager.put(myFile.getBytes(),key,getUpToken());
        map.put("code",0);  //上传成功。。
        map.put("newFileName",yuName+key);  //保存上传后的文件方法路径
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",200);  //上传失败
        }
        return map;
    }


}
	

