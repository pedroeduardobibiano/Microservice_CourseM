package com.ead.coursem.services;

import com.ead.coursem.dto.ModuleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ModuleService {

    ModuleDTO findModuleByCourse(UUID id, UUID moduleId);

    ModuleDTO save(UUID id, ModuleDTO moduleDTO);

    void delete(UUID id, UUID moduleId);

    Page<ModuleDTO> findAll(UUID id, String title,  Pageable pageable);
}
