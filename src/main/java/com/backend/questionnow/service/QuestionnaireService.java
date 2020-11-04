package com.backend.questionnow.service;

import com.backend.questionnow.dto.QuestionnaireDto;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.repository.QuestionnaireRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionnaireService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Iterable<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    public Questionnaire getQuestionnaireById(Long questionnaireId) throws NotFoundException {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        return questionnaire.orElseThrow(() -> new NotFoundException("Not Found questionnaire with id: "+questionnaireId ));
    }

    public QuestionnaireDto getStartQuestionnaire(Long questionnaireId) throws NotFoundException {
        Questionnaire questionnaire = getQuestionnaireById(questionnaireId);
        return mapQuestionnaireToQuestionnaireDto(questionnaire);
    }

    private QuestionnaireDto mapQuestionnaireToQuestionnaireDto(Questionnaire questionnaire) throws NotFoundException
    {
        return new QuestionnaireDto(questionnaire.getQuestionnaireId(),questionnaire.getName(),questionnaire.getFirstQuestion());
    }
}
