package com.zilansw.zilanshop;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zilansw.zilanshop.commons.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.List;
@Configuration
@SpringBootApplication
public class ZilanshopApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ZilanshopApplication.class, args);
    }

    /**
     * 配置FastJSON作为JSON默认解析器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*
         * 1、需要先定义一个convert转换消息的对象 2、添加fastJson的配置信息，比如：是否要格式化返回json数据 3、在convert中添加配置信息
         * 4、将convert添加到converters当中
         *
         */
        // 1、需要先定义一个·convert转换消息的对象；
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息，比如 是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert添加到converters当中.
        converters.add(fastConverter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:"+ Constant.UPLOADPATH);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
