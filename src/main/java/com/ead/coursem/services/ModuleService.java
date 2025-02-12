package com.ead.coursem.services;

import com.ead.coursem.dto.ModuleDTO;
import com.ead.coursem.models.ModuleModel;

import java.util.List;
import java.util.UUID;

public interface ModuleService {

    List<ModuleDTO>  findAll(UUID id);

    ModuleDTO  findModuleByCourse(UUID id, UUID moduleId);

    ModuleDTO save(UUID id, ModuleDTO moduleDTO);

    void delete(UUID id, UUID moduleId);

}
