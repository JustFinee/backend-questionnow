package com.backend.questionnow.dto;

import com.backend.questionnow.entity.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionnaireDto {

    Long questionnaireDtoId;
    String name;
    Question firstQuestion;

    public QuestionnaireDto(Long questionnaireDtoId, String name, Question firstQuestion) {
        this.questionnaireDtoId = questionnaireDtoId;
        this.name = name;
        this.firstQuestion = firstQuestion;
    }
}
