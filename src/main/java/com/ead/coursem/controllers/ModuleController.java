package com.ead.coursem.controllers;

import com.ead.coursem.dto.ModuleDTO;
import com.ead.coursem.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("/courses/{id}/modules")
    public ResponseEntity<Page<ModuleDTO>> findAllModules(@PathVariable(value = "id") UUID id,
                                                          @RequestParam(value = "title", defaultValue = "") String title,
                                                          @PageableDefault(sort = "module_id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ModuleDTO> moduleDTO = moduleService.findAll(id, title.trim(), pageable);
        return new ResponseEntity<>(moduleDTO, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}/modules/{moduleId}")
    public ResponseEntity<ModuleDTO> findModulesById(@PathVariable(value = "id") UUID id,
                                                     @PathVariable(value = "moduleId") UUID moduleId) {
        ModuleDTO moduleDTO = moduleService.findModuleByCourse(id, moduleId);
        return new ResponseEntity<>(moduleDTO, HttpStatus.OK);

    }


    @PostMapping("/courses/{id}/modules")
    public ResponseEntity<ModuleDTO> save(@Valid @PathVariable(value = "id") UUID id, @RequestBody ModuleDTO module) {
        ModuleDTO moduleDTO = moduleService.save(id, module);
        return new ResponseEntity<>(moduleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/courses/{id}/modules/{moduleId}")
    public ResponseEntity<ModuleDTO> delete(@Valid
                                            @PathVariable(value = "id") UUID id,
                                            @PathVariable(value = "moduleId") UUID moduleId) {
        moduleService.delete(id, moduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
