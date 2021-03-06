package me.lozm;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBatchProcessing
@SpringBootApplication
public class PlaygroundBatchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = new SpringApplicationBuilder(PlaygroundBatchApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.exit(SpringApplication.exit(application));
    }

}
