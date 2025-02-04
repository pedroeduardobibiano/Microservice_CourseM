package com.ead.coursem.services.impl;

import com.ead.coursem.repositories.ModuleRepository;
import com.ead.coursem.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

}
