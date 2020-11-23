package com.backend.questionnow.service;

import com.backend.questionnow.entity.Answer;
import com.backend.questionnow.entity.Question;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.repository.QuestionRepository;
import com.backend.questionnow.security.CustomException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    AnswerService answerService;

    public Question saveQuestion(Question question, Long questionnaireId) throws CustomException {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        questionnaire.addQuestion(question);
        questionnaireService.saveQuestionnaire(questionnaire);
        return question;
    }

    public Question findQuestionById(Long questionId)
    {
        return questionRepository.findById(questionId).orElseThrow(() -> new CustomException("NotFoundQuestion","There is no question with questionNumber: "
                +questionId, HttpStatus.NOT_FOUND));
    }

    public Question getNextQuestion(Long questionnaireId, Long questionNumber,Long answerNumber, Long questionId) throws CustomException
    {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        answerService.addCounter(questionId,answerNumber);

        return questionnaire.getQuestionList().stream()
                .filter(question -> question.getQuestionNumber() == questionNumber)
                .findFirst()
                .orElseThrow(() -> new CustomException("NotFoundQuestionInQuestionnaireException","There is no question with questionNumber: "
                        +questionNumber + " in questionnary with id: "
                        +questionnaireId, HttpStatus.NOT_FOUND));
    }
}
