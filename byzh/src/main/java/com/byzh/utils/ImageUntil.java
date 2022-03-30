package com.byzh.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageUntil {
    public boolean saveImage(String username, MultipartFile imgFile) throws Exception {
        String fileName = username + ".jpg";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        File f2 = new File(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "uploadFile");
        boolean exists2 = f2.exists();
        if (!exists2) {
            f2.mkdirs();
        }
        //String realPath=request.getSession().getServletContext().getRealPath("uploadFile/touxiang")+ File.separator;
        String tomcaturl = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "uploadFile" + File.separator + "touxiang" + File.separator;
        File f1 = new File(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "uploadFile" + File.separator + "touxiang");
        boolean exists = f1.exists();
        if (!exists) {
            f1.mkdirs();
        }
        File file = new File(tomcaturl + fileName);
        OutputStream out = new FileOutputStream(file);
        out.write(imgFile.getBytes());
        out.flush();
        out.close();
        return true;
    }/*catch(Exception e){
        e.printStackTrace();
        return false;*/
    }



