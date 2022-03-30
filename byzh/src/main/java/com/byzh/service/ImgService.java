package com.byzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byzh.pojo.Img;

import java.io.File;

public interface ImgService extends IService<Img> {
    Boolean upload(byte[] bytes);
    byte[] getByteFromFile (File file);
}
