package com.ead.coursem.controllers;

import com.ead.coursem.dto.CourseDTO;
import com.ead.coursem.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "courses")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ControllerCourse {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTO = courseService.findAll();
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> FindCourseById(@PathVariable UUID id) {
        CourseDTO courseDTO = courseService.findById(id);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.save(courseDTO);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable(value = "id") UUID id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @PathVariable(value = "id") UUID id, @RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.update(id, courseDTO);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }



}
