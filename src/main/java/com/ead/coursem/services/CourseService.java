package com.ead.coursem.services;

import com.ead.coursem.dto.CourseDTO;
import com.ead.coursem.models.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface CourseService {

    Page<CourseDTO> findAll(Specification<CourseModel> spec, Pageable pageable);

    CourseDTO findById(UUID id);

    CourseDTO save(CourseDTO courseDTO);

    void delete(UUID id);

    CourseDTO update(UUID id, CourseDTO courseDTO);


}
