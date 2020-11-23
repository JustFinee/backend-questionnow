package com.backend.questionnow.service;

import com.backend.questionnow.entity.Answer;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    AnswerRepository answerRepository;

    public Answer addAnswer(Long questionnaireId,Long questionId, Answer answer){
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        questionnaire.getQuestionList().stream()
                .filter(quest -> quest.getQuestionId()==questionId).findFirst().ifPresent(x ->x.getAnswerList().add(answer));
       questionnaireService.saveQuestionnaire(questionnaire);
        return answer;
    }

    public void addCounter(Long questionId, Long answerNumber)
    {
        Answer answerChange = questionService.findQuestionById(questionId).getAnswerList().stream()
                .filter(answer -> answer.getAnswerNumber()==answerNumber).findFirst().get();
        answerChange.setCounter(answerChange.getCounter()+1);
        saveAnswer(answerChange);
    }

    public void saveAnswer(Answer answer)
    {
        answerRepository.save(answer);
    }
}
