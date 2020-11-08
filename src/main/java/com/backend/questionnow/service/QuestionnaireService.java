package com.backend.questionnow.service;

import com.backend.questionnow.dto.QuestionnaireDto;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.entity.User;
import com.backend.questionnow.repository.QuestionnaireRepository;
import com.backend.questionnow.security.CustomException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionnaireService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    UserService userService;

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public List<QuestionnaireDto> getAllUserQuestionnaires(Long userId) throws CustomException {

        User user = userService.findUserById(userId);
        List<Questionnaire> listQuestionnaire = user.getQuestionnaireList();
        return mapQuestionnaireListToQuestionnaireDtoList(listQuestionnaire);
    }

    public Questionnaire getQuestionnaireById(Long questionnaireId) throws CustomException {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        return questionnaire.orElseThrow(() -> new CustomException("NotFoundQuestionnaireException","Not Found questionnaire with id: " + questionnaireId, HttpStatus.NOT_FOUND));
    }

    public QuestionnaireDto getStartQuestionnaire(Long questionnaireId) throws CustomException {
        Questionnaire questionnaire = getQuestionnaireById(questionnaireId);
        return mapQuestionnaireToQuestionnaireDto(questionnaire);
    }

    private QuestionnaireDto mapQuestionnaireToQuestionnaireDto(Questionnaire questionnaire) {
        return new QuestionnaireDto(questionnaire.getQuestionnaireId(), questionnaire.getName(), questionnaire.getFirstQuestion());
    }

    private List<QuestionnaireDto> mapQuestionnaireListToQuestionnaireDtoList(List<Questionnaire> questionnaireList) {
        List<QuestionnaireDto> questionnaireDtoList = new ArrayList<>();
        questionnaireList.stream()
                .forEach(questionnaire -> {
                    try {
                        questionnaireDtoList.add(mapQuestionnaireToQuestionnaireDto(questionnaire));
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                });
        return questionnaireDtoList;
    }
}
