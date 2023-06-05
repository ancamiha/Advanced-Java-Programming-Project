package com.api.sweetshop;

import com.api.sweetshop.model.Sweets;
import com.api.sweetshop.service.Baking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SweetshopApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml", "test-sweets.xml");
        Baking baking = initialize(context);
        printExistingSweets(baking);

        SpringApplication.run(SweetshopApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/SweetShop/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
        };
    }

    public static Baking initialize(ApplicationContext context) {
        Baking banking = (Baking) context.getBean("baking");
        Sweets sweets1 = (Sweets) context.getBean("sweet1");
        Sweets sweets2 = (Sweets) context.getBean("sweet2");
        Sweets sweets3 = (Sweets) context.getBean("sweet3");

        banking.addSweet(sweets1);
        banking.addSweet(sweets2);
        banking.addSweet(sweets3);

        return banking;
    }

    private static void printExistingSweets(Baking baking) {
        System.out.println("~~~Sweets~~~");

        baking.getAll()
                .forEach(sweets -> System.out.println(sweets.getName() + " | " + sweets.getDescription() + " | " + sweets.getPrice()));
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("application.properties"));
        return placeholderConfigurer;
    }
}
