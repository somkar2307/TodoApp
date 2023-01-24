package com.rest.webservices.restfulwebservices.HelloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.models.HelloWorldBean;

@RestController
public class HelloWorld {

	@Autowired
	private MessageSource source;
	
	@RequestMapping("/hello-world")
	public String HelloWorld() {
		return "Hello World";
	}
	
	@RequestMapping("hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello OMkar");
	}
	
	@RequestMapping("/hello-world-internationalized")
	public String HelloWorldInternationalized() {
		Locale locale=LocaleContextHolder.getLocale();
		return source.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
