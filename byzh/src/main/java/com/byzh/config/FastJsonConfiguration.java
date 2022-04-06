//package com.byzh.config;

/*
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * WebMvcConfigurerAdapter类是SpringBoot内部提供专门处理用户自行添加的配置，
 * 里面不仅仅包含了修改视图的过滤还有其他很多的方法，还包括拦截器，过滤器，Cors配置等。
 * Created by hengyang4 on 2018/7/9.
 *//*

@Configuration
public class FastJsonConfiguration extends WebMvcConfigurerAdapter {

    */
/**
     * 修改自定义消息转化器
     * @param converters 消息转换器列表
     *//*

    @Bean
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //过滤并修改配置返回内容
        fastJsonConfig.setSerializerFeatures(
                //List字段如果为null,输出为[],而非null
                //SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //Boolean字段如果为null,输出为falseJ,而非null
                //SerializerFeature.WriteNullBooleanAsFalse,
                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //是否输出值为null的字段,默认为false。
                SerializerFeature.WriteMapNullValue
        );
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastJsonConverter);
    }
}*/
