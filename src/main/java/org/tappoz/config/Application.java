package org.tappoz.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "org.tappoz")
@EnableAutoConfiguration
@EnableWebMvc
public class Application extends SpringBootServletInitializer {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        SpringApplicationBuilder springApplicationBuilder = application.sources(Application.class);
        return springApplicationBuilder;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);
        log.debug("~*~*~*~* BEGIN the beans provided by Spring Boot: ");
        String[] beanNames = configurableApplicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            log.debug(beanName);
        }
        log.debug("~*~*~*~* END the beans provided by Spring Boot.");
    }

}
