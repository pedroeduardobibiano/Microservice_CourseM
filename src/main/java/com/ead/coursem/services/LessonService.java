package com.ead.coursem.services;

import com.ead.coursem.dto.LessonsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LessonService {

    Page<LessonsDTO> findAllLessons(UUID moduleId, String title, Pageable pageable);

    LessonsDTO save(UUID moduleId, LessonsDTO lessonsDTO);

    LessonsDTO update(UUID moduleId, UUID lessonId, LessonsDTO lessonsDTO);

    void delete(UUID moduleId, UUID lessonId);

    LessonsDTO findById(UUID moduleId, UUID lessonId);


}
