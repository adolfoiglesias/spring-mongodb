package com.amichdev.spring.mongodb.basic.controller;

import com.amichdev.spring.mongodb.basic.domain.Course;
import com.amichdev.spring.mongodb.basic.repository.CourseRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Courses", description = "Courses Api using Spring WebServices")
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    final private CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<List> getAllCourses() {
        return ResponseEntity.ok(courseRepository.findAll()) ;
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        Optional<Course> user = courseRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseRepository.save(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable UUID courseId, @RequestBody Course updatedCourse) {

        courseRepository.deleteById(courseId);
        updatedCourse.setId(courseId);
        Course course = courseRepository.save(updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable UUID courseId) {
        courseRepository.deleteById(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{courseName}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String courseName) {
        Optional<Course> user = courseRepository.findByName(courseName);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{category}")
    public ResponseEntity<List> getCourseByCategory(@PathVariable String category) {
        var courses = courseRepository.findByCategory(category);
        return ResponseEntity.ok(courses);
    }


}
