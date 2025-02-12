package com.ead.coursem.services;

import com.ead.coursem.dto.LessonsDTO;

import java.util.List;
import java.util.UUID;

public interface LessonService {

    List<LessonsDTO> findAllLessons(UUID moduleId);

    LessonsDTO save(UUID moduleId, LessonsDTO lessonsDTO);

    LessonsDTO update(UUID moduleId, UUID lessonId, LessonsDTO lessonsDTO);

    void delete(UUID moduleId, UUID lessonId);

    LessonsDTO findById(UUID moduleId, UUID lessonId);


}
