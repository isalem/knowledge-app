package com.ness.knowledges.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import com.ness.knowledges.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class ApplicationConfiguration {

}
