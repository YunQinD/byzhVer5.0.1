package com.byzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byzh.pojo.Sentences;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SentencesMapper extends BaseMapper<Sentences> {

    //获取语句id
    @Select("SELECT id FROM everyday_sentences WHERE everyday_sentences.flag = #{flag};")
    List<Integer> getList (Integer flag);

    //获取数量
    @Select("SELECT count(everyday_sentences.id) FROM everyday_sentences WHERE everyday_sentences.flag = #{flag};")
    Integer getAccount (Integer flag);

    //按id获取语句
    @Select("select * from everyday_sentences where everyday_sentences.id = #{id};")
    Sentences getSentences (Integer id);
}
