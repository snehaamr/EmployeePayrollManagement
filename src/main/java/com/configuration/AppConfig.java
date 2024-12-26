package com.siddhrans.biometric.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.siddhrans.biometric.convertor.DeptStrToDeptConverter;
import com.siddhrans.biometric.convertor.DesignationToUserConverter;
import com.siddhrans.biometric.convertor.LeaveTypeStrToLeaveType;
import com.siddhrans.biometric.convertor.MachineStrToMachineConverter;
import com.siddhrans.biometric.convertor.RoleToUserProfileConverter;
import com.siddhrans.biometric.convertor.SalDivStrToSalDivConverter;
import com.siddhrans.biometric.convertor.UserStrToUserConverter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.siddhrans.biometric")
public class AppConfig extends WebMvcConfigurerAdapter{


	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
	@Autowired
	DeptStrToDeptConverter deptStrToDeptConverter;
	
	@Autowired
	DesignationToUserConverter designationToUserConverter;
	
	@Autowired
	SalDivStrToSalDivConverter salDivStrToSalDivConverter;
	
	@Autowired
	LeaveTypeStrToLeaveType leaveTypeStrToLeaveType;
	
	@Autowired
	UserStrToUserConverter userStrToUserConverter;
	
	@Autowired
	MachineStrToMachineConverter machineStrToMachineConverter;


	@Bean(name="multipartResolver")
	public StandardServletMultipartResolver resolver(){
		return new StandardServletMultipartResolver();
	}
	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/**
	 * Configure Converter to be used.
	 * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
		registry.addConverter(deptStrToDeptConverter);
		registry.addConverter(designationToUserConverter);
		registry.addConverter(salDivStrToSalDivConverter);
		registry.addConverter(leaveTypeStrToLeaveType);
		registry.addConverter(userStrToUserConverter);
		registry.addConverter(machineStrToMachineConverter);
	}


	/**
	 * Configure MessageSource to lookup any validation/error message in internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
	 * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
	 * This is a workaround for this issue.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}