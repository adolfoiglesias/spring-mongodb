package com.amichdev.spring.mongodb.basic.repository;

import com.amichdev.spring.mongodb.basic.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends MongoRepository<Course, UUID> {

    public Optional<Course> findByName(String name);

    public List<Course> findByCategory(String category);
}
