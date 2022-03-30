package com.byzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byzh.pojo.Img;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface ImgMapper extends BaseMapper<Img> {
}
