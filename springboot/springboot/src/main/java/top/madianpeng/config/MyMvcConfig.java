package top.madianpeng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import top.madianpeng.interceptor.LoginHandlerInterceptor;

@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/tologin").setViewName("user/login");
		registry.addViewController("/main").setViewName("index");
		registry.addViewController("/console").setViewName("home/homepage2");
		registry.addViewController("/staff").setViewName("basic/staff/staff");
		registry.addViewController("/subarea").setViewName("basic/subarea/subarea");
		registry.addViewController("/decidezone").setViewName("basic/decidezone/decidedzone");
		registry.addViewController("/subareaform").setViewName("basic/subarea/subareaform");
		registry.addViewController("/decidezoneform").setViewName("basic/decidezone/decidedzoneform");
		registry.addViewController("/staffform").setViewName("basic/staff/staffform");
		registry.addViewController("/userlist").setViewName("user/user/list");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/imgs/**").addResourceLocations("file:D:/imgs/");
	}

	/*
	 * @Override protected void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(new
	 * LoginHandlerInterceptor()).addPathPatterns("/*").excludePathPatterns(
	 * "/tologin", "/user/login", "/druid/*"); }
	 */

}
