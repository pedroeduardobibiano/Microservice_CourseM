package com.ead.coursem.repositories;

import com.ead.coursem.models.LessonModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

    @Query(value = "select * from tb_lesson " +
            "where module_module_id = :moduleId", nativeQuery = true)
    List<LessonModel> findAllLessonsIntoModule(@Param("moduleId") UUID moduleId);

    @Query(value = "select * from tb_lesson " +
            "where module_module_id = :moduleId " +
            "and lower(title) like lower(concat('%', :title ,'%')) ", nativeQuery = true)
    Page<LessonModel> findAllLessonsIntoModulePaged(@Param("moduleId") UUID moduleId, String title, Pageable pageable);

    @Query(value = "select * from tb_lesson " +
            "where module_module_id = :moduleId and lesson_id = :lessonId", nativeQuery = true)
    LessonModel findLessonsById(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId);


}
