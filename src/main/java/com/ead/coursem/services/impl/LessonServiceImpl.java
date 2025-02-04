package com.ead.coursem.services.impl;

import com.ead.coursem.repositories.LessonRepository;
import com.ead.coursem.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

}
