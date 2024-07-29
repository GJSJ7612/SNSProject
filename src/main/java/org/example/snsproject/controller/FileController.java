package org.example.snsproject.controller;

import org.example.snsproject.entity.Result;
import org.example.snsproject.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.UUID;

@RestController
public class FileController {

    @PostMapping("/upload")
    public Result<String> uploadImage(MultipartFile image) {
        try{
            //把文件的内容存储到本地磁盘上
            String originalFilename = image.getOriginalFilename();
            //保证文章名称唯一
            String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //image.transferTo(new File("E:\\JAVA Project\\SNSProject\\src\\main\\resources\\static\\img\\" + filename));
            String url = AliOssUtil.uploadFile(filename, image.getInputStream());
            return Result.success(url);
        }catch (Exception e){
            return Result.error(1);
        }
    }
}
