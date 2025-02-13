package com.ead.coursem.services.impl;

import com.ead.coursem.dto.CourseDTO;
import com.ead.coursem.models.CourseModel;
import com.ead.coursem.models.LessonModel;
import com.ead.coursem.models.ModuleModel;
import com.ead.coursem.repositories.CourseRepository;
import com.ead.coursem.repositories.LessonRepository;
import com.ead.coursem.repositories.ModuleRepository;
import com.ead.coursem.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;


    @Override
    public Page<CourseDTO> findAll(Specification<CourseModel> spec, Pageable pageable) {
        Page<CourseModel> courses = courseRepository.findAll(spec, pageable);
        return courses.map(CourseDTO::new);

    }

    @Transactional(readOnly = true)
    @Override
    public CourseDTO findById(UUID id) {
        CourseModel courseModel = getCourseModel(id);
        return new CourseDTO(courseModel);
    }

    @Transactional
    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        CourseModel courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDTO, courseModel);
        courseModel.setCourseStatus(courseDTO.getCourseStatus());
        courseModel.setCourseLevel(courseDTO.getCourseLevel());
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        CourseModel save = courseRepository.save(courseModel);
        return new CourseDTO(save);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        CourseModel courseModel = getCourseModel(id);
        List<ModuleModel> models = moduleRepository.findAllModulesIntoCourse(courseModel.getId());
        for (ModuleModel module : models) {
            List<LessonModel> lessons = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
            lessonRepository.deleteAll(lessons);
            moduleRepository.deleteAll(models);
        }
        courseRepository.delete(courseModel);
    }

    @Transactional
    @Override
    public CourseDTO update(UUID id, CourseDTO courseDTO) {
        CourseModel courseModel = getCourseModel(id);
        BeanUtils.copyProperties(courseDTO, courseModel, "id");
        courseModel.setCourseStatus(courseDTO.getCourseStatus());
        courseModel.setCourseLevel(courseDTO.getCourseLevel());
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel = courseRepository.save(courseModel);
        return new CourseDTO(courseModel);
    }


    private CourseModel getCourseModel(UUID id) {
        Optional<CourseModel> courseModel = courseRepository.findById(id);
        return courseModel.orElseThrow(() -> new EntityNotFoundException("Id not found"));
    }

}
