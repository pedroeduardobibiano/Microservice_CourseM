package com.ead.coursem.repositories;

import com.ead.coursem.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {

    @Query(value = "select * from tb_module " +
            "where course_id = :id", nativeQuery = true)
    List<ModuleModel> findAllModulesIntoCourse(@Param("id") UUID id);

    @Query(value = "select * from tb_module " +
            "where course_id = :id " +
            "and lower(title) like lower(concat('%', :title ,'%'))", nativeQuery = true)
    Page<ModuleModel> findAllModulesIntoCoursePaged(@Param("id") UUID id, String title, Pageable pageable);

    @Query(value = "select * from tb_module " +
            "where course_id = :id and module_id = :moduleId", nativeQuery = true)
    Optional<ModuleModel> findModuleIntoCourse(@Param("id") UUID id,
                                               @Param("moduleId") UUID moduleId);

}
