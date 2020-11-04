package com.backend.questionnow.service;

import com.backend.questionnow.entity.Question;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.repository.QuestionRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionnaireService questionnaireService;

    public Question saveQuestion(Question question, Long questionnaireId) throws NotFoundException {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        questionnaire.addQuestion(question);
        questionnaireService.saveQuestionnaire(questionnaire);
        return question;
    }

    public Question getNextQuestion(Long questionnaireId, Long questionNumber) throws NotFoundException
    {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        return questionnaire.getQuestionList().stream()
                .filter(question -> question.getQuestionNumber() == questionNumber)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("There is no question with questionNumber: "
                        +questionNumber + " in questionnary with id: "
                        +questionnaireId));
    }
}
