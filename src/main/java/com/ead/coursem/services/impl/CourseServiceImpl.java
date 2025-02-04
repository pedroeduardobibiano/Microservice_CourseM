package com.ead.coursem.services.impl;

import com.ead.coursem.repositories.CourseRepository;
import com.ead.coursem.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

}
