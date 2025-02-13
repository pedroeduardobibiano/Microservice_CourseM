package com.ead.coursem.services.impl;

import com.ead.coursem.dto.ModuleDTO;
import com.ead.coursem.models.CourseModel;
import com.ead.coursem.models.LessonModel;
import com.ead.coursem.models.ModuleModel;
import com.ead.coursem.repositories.CourseRepository;
import com.ead.coursem.repositories.LessonRepository;
import com.ead.coursem.repositories.ModuleRepository;
import com.ead.coursem.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<ModuleDTO> findAll(UUID id, String title,  Pageable pageable) {
        Page<ModuleModel> module = moduleRepository.findAllModulesIntoCoursePaged(id, title ,pageable);
        return module.map(ModuleDTO::new);
    }


    @Transactional(readOnly = true)
    @Override
    public ModuleDTO findModuleByCourse(UUID id, UUID moduleId) {
        Optional<ModuleModel> module = moduleRepository.findModuleIntoCourse(id, moduleId);
        module.orElseThrow(() -> new EntityNotFoundException("Module not found"));
        return new ModuleDTO(module.get());
    }


    @Transactional
    @Override
    public ModuleDTO save(UUID id, ModuleDTO moduleDTO) {
        CourseModel courseModel = getCourseModel(id);
        ModuleModel module = new ModuleModel();
        BeanUtils.copyProperties(moduleDTO, module);
        module.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        module.setCourse(courseModel);
        moduleRepository.save(module);
        return new ModuleDTO(module);
    }

    @Transactional
    @Override
    public void delete(UUID id, UUID moduleId) {
        ModuleModel moduleModel = getModuleId(moduleId);
        List<LessonModel> lessons = lessonRepository.findAllLessonsIntoModule(moduleId);
        lessonRepository.deleteAll(lessons);
        moduleRepository.delete(moduleModel);

    }


    private ModuleModel getModuleId(UUID id) {
        Optional<ModuleModel> moduleModel = moduleRepository.findById(id);
        return moduleModel.orElseThrow(() -> new EntityNotFoundException("ModuleId not found"));
    }

    private CourseModel getCourseModel(UUID id) {
        Optional<CourseModel> courseModel = courseRepository.findById(id);
        return courseModel.orElseThrow(() -> new EntityNotFoundException("CourseId not found"));
    }
}
