package com.ead.coursem.dto;

import com.ead.coursem.models.LessonModel;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonsDTO {


    private UUID lessonId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String videoUrl;

    public LessonsDTO(LessonModel lessonModel) {
        this.lessonId = lessonModel.getLessonId();
        this.title = lessonModel.getTitle();
        this.description = lessonModel.getDescription();
        this.videoUrl = lessonModel.getVideoUrl();
    }


}
