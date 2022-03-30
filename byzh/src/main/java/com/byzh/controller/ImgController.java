package com.byzh.controller;

import com.byzh.Mapper.UserMapper;
import com.byzh.service.ImgService;
import com.byzh.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Calendar;

@Log4j2
@RestController
@RequestMapping("saveProfiles")

public class ImgController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping //返回json数据
    public R uploadProfile(@RequestParam("file") MultipartFile file, @RequestParam("uid") String uid,HttpServletRequest request) {

        Calendar cal=Calendar.getInstance();
        Integer y =cal.get(Calendar.YEAR);
        Integer m =cal.get(Calendar.MONTH);
        Integer d =cal.get(Calendar.DATE);
        Integer h =cal.get(Calendar.HOUR_OF_DAY);
        Integer mi =cal.get(Calendar.MINUTE);
        Integer s =cal.get(Calendar.SECOND);
        String datetime = y.toString() + m.toString() + d.toString() + h.toString() + mi.toString();

        String contentType = file.getContentType();
        String fileName = uid + file.getOriginalFilename();
        String staticUrl = "http://101.201.36.172:8080";
        String filePath =  "/profile";


        if (file.isEmpty()) {
            return new R(false, "文件为空");
        }

        try {
            uploadFile(file.getBytes(), filePath, fileName);
            String url = staticUrl + "/images" + "/" + fileName;
            if(userMapper.updateProfileByUid(url,uid) && userMapper.updatePostProfileByUid(url,uid)){
                return new R(false,"头像更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new R(true, "上传成功");
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

        File targetFile = new File(filePath);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();

    }
}

    /*public static byte[] compressImage(byte[] imageByte, int ppi) throws Exception {
        byte[] smallImage = null;
        int width = 0, height = 0;

        if (imageByte == null){
            return null;}

        ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
        try {
            Image image = ImageIO.read(byteInput);
            int w = image.getWidth(null);
            int h = image.getHeight(null);
            // adjust weight and height to avoid image distortion
            double scale = 0;
            scale = Math.min((float) ppi / w, (float) ppi / h);
            width = (int) (w * scale);
            width -= width % 4;
            height = (int) (h * scale);

            if (scale >= (double) 1){
                return imageByte;}

            BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            buffImg.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "png", out);
            smallImage = out.toByteArray();
            return smallImage;

        }catch (IOException e) {
            log.error(e.getMessage());
            throw new Exception("");
        }*//*

    }*/







    /*@ResponseBody
    public class ImgController {
        @Autowired
        private ImgService imgService;

        public R upLoadImg(@RequestParam("images")File file, HttpServletRequest request){
            if(imgService.getByteFromFile(file)==null){
                return new R(false,null);
            }
            return new R(true,imgService.getByteFromFile(file));
        }

    }*/
