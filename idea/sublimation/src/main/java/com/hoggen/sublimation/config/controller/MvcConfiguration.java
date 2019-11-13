package com.hoggen.sublimation.config.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements ApplicationContextAware, WebMvcConfigurer {
    private ApplicationContext applicationContext;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private RoleInterceptor roleInterceptor;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.applicationContext = applicationContext;
    }

    /**
     * @注释 静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/v1/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * @注释 定义默认请求
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // TODO Auto-generated method stub
        configurer.enable();
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**")
//                .excludePathPatterns("/api/login/**")
//                .excludePathPatterns("/api/stock/temp_insert")
//                .excludePathPatterns("/api/operation/temp_insert")
//                .excludePathPatterns("/api/sampler/addTemSample")
//                .excludePathPatterns("/api/sampler/addTemSepSample")
//                .excludePathPatterns("/api/admin/rubbish_company/records")
//                .excludePathPatterns("/api/config");
//
//        registry.addInterceptor(roleInterceptor).addPathPatterns("/api/**")
//                .excludePathPatterns("/api/login/**")
//                .excludePathPatterns("/api/stock/temp_insert")
//                .excludePathPatterns("/api/operation/temp_insert")
//                .excludePathPatterns("/api/sampler/addTemSample")
//                .excludePathPatterns("/api/sampler/addTemSepSample")
//                .excludePathPatterns("/api/admin/rubbish_company/records")
//                .excludePathPatterns("/api/config");


        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * @注释 定义视图解析器
     */

    @Bean(name = "viewResolver")
    public ViewResolver setViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 设置sPring容器！
        viewResolver.setApplicationContext(this.applicationContext);
        // 取消缓存
        viewResolver.setCache(false);
        // 设置解析的前缀
        viewResolver.setPrefix("/WEB-INF/v1/");
        // 设置解析的后缀
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


//    /**
//     * @注释 文件上传解析器
//     */
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver setCommonsMultipartResolver() {
//        CommonsMultipartResolver viewResolver = new CommonsMultipartResolver();
//
//        viewResolver.setDefaultEncoding("utf-8");
//
//        viewResolver.setMaxUploadSize(20971520);
//        viewResolver.setMaxInMemorySize(20971520);
//        return viewResolver;
//    }

}