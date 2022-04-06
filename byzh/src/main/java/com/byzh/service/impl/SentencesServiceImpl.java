package com.byzh.service.impl;

import com.byzh.Mapper.SentencesMapper;
import com.byzh.pojo.Sentences;
import com.byzh.service.SentencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SentencesServiceImpl implements SentencesService {
    @Autowired
    private SentencesMapper sentencesMapper;

    @Override
    public Sentences getSentence(Integer type) {
        Random random = new Random();
        Integer count = sentencesMapper.getAccount(type);
        List<Integer> List = sentencesMapper.getList(type);
        return sentencesMapper.getSentences(List.get(random.nextInt(count)));
    }

}
