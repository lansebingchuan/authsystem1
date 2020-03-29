package com.zht.controller;

import com.zht.bean.User;
import com.zht.constant.ConstantInterface;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zht
 * @create 2019-12-07 23:20
 */
@RestController
public class UploadFileController extends BaseController {

    @RequestMapping("/uploadFile")
    public Object uploadFile(MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session, String type) throws IOException {
        start();
        User user = SessionUtil.getUser(session);
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("upFile");
        if (multipartFile != null){
            String oldFileName = multipartFile.getOriginalFilename();
            String saveFilePath = ConstantInterface.FILEPATH;
            String headPath = null;
            if (type != null && "head".equals(type)){
                headPath = ConstantInterface.HEAD;
            }else if(type != null && "product".equals(type)){
                headPath = ConstantInterface.PRODUCT;
            }else {
                flage(false);
                return end();
            }
            saveFilePath+=headPath;
            String sysPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static"+saveFilePath;
            File sysFilePath = new File(sysPath);
            if (!sysFilePath.exists()){
                sysFilePath.mkdir();
            }
            String fileUuid = UUID.randomUUID().toString();
            String newFileName = fileUuid + oldFileName.substring(oldFileName.lastIndexOf("."));
            File file = new File(sysPath,newFileName);
            if (!file.exists()){
                file.createNewFile();
            }
            multipartFile.transferTo(file);
            flage(true);
            put("fileName", newFileName);
            put("filePath", saveFilePath+File.separator+newFileName);
            return end();
        }
        flage(false);
        return end();
    }
}
