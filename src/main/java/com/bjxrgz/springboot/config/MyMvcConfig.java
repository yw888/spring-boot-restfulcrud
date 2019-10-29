package com.bjxrgz.springboot.config;



import com.bjxrgz.springboot.component.LoginHandlerInterceptor;
import com.bjxrgz.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送/ceshi,映射到success页面
        registry.addViewController("/ceshi").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/index.html","/","/user/login","/webjars/**","/asserts/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public LocaleResolver localeResolver(){//名字必须为localeResolver
        return new MyLocaleResolver();
    }
//    WebMvcConfigurationSupport-->不需要返回逻辑视图,可以选择继承此类
//    WebMvcCofigurer-->返回逻辑视图,可以选择实现此方法,重写addInterceptor方法
//    下边是继承WebMvcConfigurationSupport写法

    //    @Override
//    protected void addViewControllers(ViewControllerRegistry registry) {
////        super.addViewControllers(registry);
//        //浏览器发送/ceshi,映射到success页面
//        registry.addViewController("/ceshi").setViewName("success");
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/index.html").setViewName("login");
//        registry.addViewController("/main.html").setViewName("dashboard");
//    }
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
//                excludePathPatterns("/index.html","/","/user/login","/webjars/**","/asserts/**");
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }
//

}
