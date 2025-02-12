package com.ead.coursem.services;

import com.ead.coursem.dto.CourseDTO;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<CourseDTO> findAll();

    CourseDTO findById(UUID id);

    CourseDTO save(CourseDTO courseDTO);

    void delete(UUID id);

    CourseDTO update(UUID id, CourseDTO courseDTO);


}
