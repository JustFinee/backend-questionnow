package com.backend.questionnow.service;

import com.backend.questionnow.entity.Answer;
import com.backend.questionnow.entity.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionnaireService questionnaireService;

    public Answer addAnswer(Long questionnaireId,Long questionId, Answer answer){
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        questionnaire.getQuestionList().stream()
                .filter(quest -> quest.getQuestionId()==questionId).findFirst().ifPresent(x ->x.getAnswerList().add(answer));
       questionnaireService.saveQuestionnaire(questionnaire);
        return answer;
    }
}
