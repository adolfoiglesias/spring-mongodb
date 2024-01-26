package com.amichdev.spring.mongodb;

import com.amichdev.spring.mongodb.basic.domain.Category;
import com.amichdev.spring.mongodb.basic.domain.Course;
import com.amichdev.spring.mongodb.basic.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SpringMongoDBBasicApplication {

    @Autowired
    CourseRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoDBBasicApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                repository.deleteAll();

                Category category = Category.builder()
                        .id(UUID.randomUUID())
                        .name("category 1")
                        .build();

                final var courseName1 = "Course 1";

                var course1 = Course.builder()
                        .id(UUID.randomUUID())
                        .name(courseName1)
                        .category(category)
                        .description("Description 1")
                        .build();

                var course2 = Course.builder()
                        .id(UUID.randomUUID())
                        .name("Course 2")
                        .description("Description 2")
                        .category(category)
                        .build();

                repository.saveAll(List.of(course1, course2));

                var courseFromDB = repository.findByName(courseName1);

                System.out.println("Data from Mongo DB ");

                System.out.println("All document on DB");
                var list = repository.findAll();
                list.forEach(System.out::println);

                System.out.println("look up course for given a course name: ");
                System.out.println(courseFromDB);

            }
        };

    }

}