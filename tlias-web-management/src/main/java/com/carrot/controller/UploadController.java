package com.carrot.controller;

import com.carrot.pojo.Result;
import com.carrot.pojo.Param.UploadParam;
import com.carrot.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 本地上传文件
     */
//    @PostMapping("/upload")
//    public Result upload(UploadParam uploadParam) throws IOException {
//        log.info("【上传文件】开始，参数：{}", uploadParam);
//        String dir = "F:/webai-data/";
//        if (!new File(dir).exists()) {
//            new File(dir).mkdirs();
//        }
//
//        MultipartFile file = uploadParam.getFile(); // 获取文件
//        String originalFilename = file.getOriginalFilename(); // 获取文件名
//        String extendsion = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件后缀
//        String newFileName = UUID.randomUUID().toString() + extendsion; // 随机生成文件名
//        file.transferTo(new File("F:/webai-data/" + newFileName)); // 保存文件
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(UploadParam uploadParam) throws Exception {
        log.info("【上传文件】开始，参数：{}", uploadParam);
        MultipartFile file = uploadParam.getFile();
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("【上传文件】成功，返回结果：{}", url);
        return Result.success(url);
    }
}
