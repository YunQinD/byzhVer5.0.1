package com.byzh.controller;

import com.byzh.service.SentencesService;
import com.byzh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sentences")
public class SentencesController {
    @Autowired
    private SentencesService sentencesService;

    @GetMapping("/{type}")
    public R getSentences(@PathVariable Integer type){
        if(type != null){
            return new R(true,sentencesService.getSentence(type));
        }
        return new R(false,"请输入类型");
    }
}
