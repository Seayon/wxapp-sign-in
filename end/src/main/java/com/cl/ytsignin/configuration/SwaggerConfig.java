package com.cl.ytsignin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/3/19
 */
@EnableSwagger2
@EnableWebMvc
@Configuration
public class SwaggerConfig {
	Contact contact = new Contact("cl", "http://yt.creatform.com", "448922320@qq.com");

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cl.ytsignin.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("羽田签到接口文档")
				.description("羽田签到小程序接口文档")
				.termsOfServiceUrl("http://yt.creatform.com")
				.contact(contact)
				.version("1.0")
				.build();
	}


}
