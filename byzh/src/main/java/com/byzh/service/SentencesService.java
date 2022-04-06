package com.byzh.service;

import com.byzh.pojo.Sentences;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SentencesService {
    //获取句子
    Sentences getSentence(Integer type);
}
