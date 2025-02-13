package com.ead.coursem.controllers;

import com.ead.coursem.dto.LessonsDTO;
import com.ead.coursem.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @GetMapping("modules/{modulesId}/lessons")
    public ResponseEntity<Page<LessonsDTO>> findAllLessons(@PathVariable(value = "modulesId") UUID id,
                                                           @RequestParam(value = "title", defaultValue = "") String title,
                                                           @PageableDefault(sort = "lesson_id") Pageable pageable) {
        Page<LessonsDTO> lessons = lessonService.findAllLessons(id, title.trim(), pageable);
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @PostMapping("modules/{modulesId}/lessons")
    public ResponseEntity<LessonsDTO> save(@PathVariable(value = "modulesId") UUID id,
                                           @RequestBody LessonsDTO lessonsDTO) {
        LessonsDTO lesson = lessonService.save(id, lessonsDTO);
        return new ResponseEntity<>(lesson, HttpStatus.CREATED);
    }

    @GetMapping("modules/{modulesId}/lessons/{lessonId}")
    public ResponseEntity<LessonsDTO> findById(@PathVariable(value = "modulesId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId) {
        LessonsDTO lesson = lessonService.findById(moduleId, lessonId);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @PutMapping("modules/{modulesId}/lessons/{lessonId}")
    public ResponseEntity<LessonsDTO> update(@PathVariable(value = "modulesId") UUID moduleId,
                                             @PathVariable(value = "lessonId") UUID lessonId,
                                             @RequestBody LessonsDTO lessonsDTO) {
        LessonsDTO lesson = lessonService.update(moduleId, lessonId, lessonsDTO);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @DeleteMapping("modules/{modulesId}/lessons/{lessonId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "modulesId") UUID moduleId,
                                       @PathVariable(value = "lessonId") UUID lessonId) {
        lessonService.delete(moduleId, lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
