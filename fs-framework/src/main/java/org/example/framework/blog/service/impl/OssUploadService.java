package org.example.framework.blog.service.impl;

import org.example.framework.blog.service.UploadService;
import org.example.framework.domain.AppHttpCodeEnum;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.SystemException;
import org.example.framework.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OssUploadService implements UploadService {

    @Autowired
    private UploadUtils uploadOss;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //判断文件类型
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        //如果判断通过上传文件到OSS
        String filePath = uploadOss.generateFilePath(originalFilename);
        ////返回文件云空间地址
        String url = uploadOss.uploadOss(img,filePath);//  2099/2/3/wqeqeqe.png

        return ResponseResult.okResult(url);
    }


}
