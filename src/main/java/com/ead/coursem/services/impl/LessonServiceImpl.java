package com.ead.coursem.services.impl;

import com.ead.coursem.dto.LessonsDTO;
import com.ead.coursem.models.LessonModel;
import com.ead.coursem.models.ModuleModel;
import com.ead.coursem.repositories.LessonRepository;
import com.ead.coursem.repositories.ModuleRepository;
import com.ead.coursem.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;


    @Transactional(readOnly = true)
    @Override
    public List<LessonsDTO> findAllLessons(UUID moduleId) {
        getModuleModel(moduleId);
        List<LessonModel> models = lessonRepository.findAllLessonsIntoModule(moduleId);
        return models.stream().map(LessonsDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public LessonsDTO findById(UUID moduleId, UUID lessonId) {
        try {
            LessonModel getLessonModel = lessonRepository.findLessonsById(moduleId, lessonId);
            return new LessonsDTO(getLessonModel);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Lesson not Found");
        }
    }


    @Transactional
    @Override
    public LessonsDTO save(UUID moduleId, LessonsDTO lessonsDTO) {
        ModuleModel getModule = getModuleModel(moduleId);

        LessonModel lessonModel = new LessonModel();
        BeanUtils.copyProperties(lessonsDTO, lessonModel);
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));

        lessonModel.setModule(getModule);
        lessonRepository.save(lessonModel);
        return new LessonsDTO(lessonModel);
    }

    @Transactional
    @Override
    public LessonsDTO update(UUID moduleId, UUID lessonId, LessonsDTO lessonsDTO) {
        try {
            LessonModel getLessonModel = lessonRepository.findLessonsById(moduleId, lessonId);
            getLessonModel.setTitle(lessonsDTO.getTitle());
            getLessonModel.setDescription(lessonsDTO.getDescription());
            getLessonModel.setVideoUrl(lessonsDTO.getVideoUrl());
            lessonRepository.save(getLessonModel);
            return new LessonsDTO(getLessonModel);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Lesson not Found");
        }
    }

    @Transactional
    @Override
    public void delete(UUID moduleId, UUID lessonId) {
        LessonModel getLessonModel = lessonRepository.findLessonsById(moduleId, lessonId);
        lessonRepository.delete(getLessonModel);
    }

    private ModuleModel getModuleModel(UUID moduleId) {
        Optional<ModuleModel> getModule = moduleRepository.findById(moduleId);
        return getModule.orElseThrow(() -> new EntityNotFoundException("Module not found"));
    }


}
