package edu.nc.tasks.tasklist_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasklistSpringApplication extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TasklistSpringApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TasklistSpringApplication.class, args);
    }
}
