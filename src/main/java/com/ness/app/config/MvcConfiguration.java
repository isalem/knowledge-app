package com.ness.app.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import com.ness.app.helper.StringToGrantedAuthorityConverter;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(ServletContextTemplateResolver resolver) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);

		Set<IDialect> additionalDialects = new HashSet<>();
		additionalDialects.add(new SpringSecurityDialect());
		additionalDialects.add(new DataAttributeDialect());

		engine.setAdditionalDialects(additionalDialects);
		return engine;
	}

	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setCache(false);
		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("WEB-INF/i18n/skillmsg");
		source.setDefaultEncoding("UTF-8");
		return source;
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		factoryBean.setValidationMessageSource(messageSource);
		return factoryBean;
	}

	@Override
	public Validator getValidator() {
		return validatorFactoryBean(messageSource());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverter(new StringToGrantedAuthorityConverter());
	}
}
