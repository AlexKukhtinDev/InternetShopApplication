package com.akukhtin.internetshop;

import com.akukhtin.internetshop.model.Draft;
import com.akukhtin.internetshop.repository.DraftRepository;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class InternetshopApplication implements CommandLineRunner {
    @Autowired
   private DraftRepository repository;
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(InternetshopApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.saveAll(Collections.
                    singletonList(new Draft("IPhone",
                            "sdfsdf324234", 3000.0, 3000.0)));
        }
    }
}
