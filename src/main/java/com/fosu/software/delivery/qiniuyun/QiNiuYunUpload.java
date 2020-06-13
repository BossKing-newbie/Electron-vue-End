package com.fosu.software.delivery.qiniuyun;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.qiniuyun
 * @Author: Boss_king
 * @CreateTime: 2020-06-12 10:48
 * @Description: 七牛云上传类
 */
public class QiNiuYunUpload {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = QiNiuYun.accessKey; //这两个登录七牛 账号里面可以找到
    private static String SECRET_KEY = QiNiuYun.secretKey;

    //要上传的空间
    private static String bucketname = QiNiuYun.bucket; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //zone0-华东，zone1-华北，zone2代表华南机房
    private static Configuration cfg = new Configuration(Zone.zone2());
    //创建上传对象
    private static UploadManager uploadManager = new UploadManager(cfg);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    /*下面是上传的具体方法，参考七牛云SDK文档和简书博客：https://www.jianshu.com/p/534389b59732?tdsourcetag=s_pctim_aiomsg*/
    public static String updateFile(MultipartFile file, String filename) throws Exception {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            InputStream inputStream=file.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }

            byte[] uploadBytes  = swapStream.toByteArray();
            try {
                Response response = uploadManager.put(uploadBytes,filename,getUpToken());
                //解析上传成功的结果
                DefaultPutRet putRet;
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return QiNiuYun.domain+"/"+ putRet.key;

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    ex2.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
