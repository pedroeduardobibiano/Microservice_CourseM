package com.ead.coursem.dto;

import com.ead.coursem.enums.CourseLevel;
import com.ead.coursem.enums.CourseStatus;
import com.ead.coursem.models.CourseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private UUID id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String imageUrl;
    @NotNull
    private CourseStatus courseStatus;
    @NotNull
    private UUID userInstructor;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private CourseLevel courseLevel;


    public CourseDTO(CourseModel courseModel) {
        this.id = courseModel.getId();
        this.name = courseModel.getName();
        this.description = courseModel.getDescription();
        this.imageUrl = courseModel.getImageUrl();
        this.courseStatus = courseModel.getCourseStatus();
        this.userInstructor = courseModel.getUserInstructor();
        this.creationDate = courseModel.getCreationDate();
        this.courseLevel = courseModel.getCourseLevel();
    }

}
