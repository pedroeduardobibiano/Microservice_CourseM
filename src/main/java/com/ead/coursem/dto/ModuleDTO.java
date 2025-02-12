package com.ead.coursem.dto;

import com.ead.coursem.models.ModuleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {

    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    public ModuleDTO(ModuleModel module) {
        this.id = module.getModuleId();
        this.title = module.getTitle();
        this.description = module.getDescription();
    }

}
