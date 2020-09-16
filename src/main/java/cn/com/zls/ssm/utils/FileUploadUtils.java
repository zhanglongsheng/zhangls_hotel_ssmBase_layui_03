package cn.com.zls.ssm.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *   文件上传的工具类
 */
public class FileUploadUtils {

    /**
     *   文件上传的方法
     * @param path  文件上传的目标文件夹路径
     * @param myFile  //被上传的源文件
     * @return  上传操作的结果
     */
    public static Map<String,Object> upload(String path, MultipartFile myFile){
        //1.新建文件上传后的map集合（上传结果）
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //1.获取源文件的输入流
            InputStream is = myFile.getInputStream();
            //2.获取源文件类型，文件后缀名
            String originalFileName = myFile.getOriginalFilename();
            //3.定义上传后的目标文件名(为了避免文件名称重复，此时使用UUID)
            String newFileName = UUID.randomUUID().toString()+"."+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            //4.通过上传路径得到上传的文件夹
            File file = new File(path);
            //4.1.若目标文件夹不存在，则创建
            if(!file.exists()){ //判断目标文件夹是否存在
                file.mkdirs();//4.2.不存在，则创建文件夹
            }
            //5.根据目标文件夹和目标文件名新建目标文件（上传后的文件）
            File newFile = new File(path,newFileName);  //空的目标文件
            //6.根据目标文件的新建其输出流对象
            FileOutputStream os = new FileOutputStream(newFile);
            //7.完成输入流到输出流的复制
            IOUtils.copy(is,os);
            //8.关闭流（先开后关）
            os.close();
            is.close();
            map.put("code",0);  //向map中装入上传的状态，成功
            map.put("newFileName",newFileName);  //向map中装入上传的目标文件名
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code",200);  //向map中装入上传的状态，异常
        }
        return map;
    }
}
