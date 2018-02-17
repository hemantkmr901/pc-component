package com.unixon.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.unixon.controller")
public class WebConfig implements WebMvcConfigurer{
		@Bean
		public InternalResourceViewResolver getResolver()
		{
			InternalResourceViewResolver ir = new InternalResourceViewResolver();
			ir.setSuffix(".jsp");
			return ir;
		}
		
		@Bean
		public MessageSource messageSource()
		{
			ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
			resource.setBasename("messages"); 
			return resource;
		}
			
		public org.springframework.validation.Validator getValidator()
		{
			LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
			validator.setValidationMessageSource(messageSource());
			return validator;
		}
		
		 @Bean
		   public MultipartResolver multipartResolver() {
		      StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		      return resolver;
		   }
		 
//		 public void addInterceptors(InterceptorRegistry register)
//			{
////				register.addInterceptor(new TextUpdateInterceptor()).addPathPatterns(new String[] {"/textUpdate","/textUpdate*"});
//				register.addInterceptor(new GetByIdInterceptor()).addPathPatterns(new String[] {"/getById","/getById*"});
//			}
}
