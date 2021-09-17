package com.vikram;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path pathDir = Paths.get("./uploads");
		String uploadPath = pathDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/"+uploadPath+"/");
	}
}