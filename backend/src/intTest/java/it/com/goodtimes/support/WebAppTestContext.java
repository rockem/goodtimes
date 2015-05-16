package it.com.goodtimes.support;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.goodtimes.events", "com.goodtimes.users"},
        excludeFilters = @ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION)
)
public class WebAppTestContext extends WebMvcConfigurerAdapter {

}

