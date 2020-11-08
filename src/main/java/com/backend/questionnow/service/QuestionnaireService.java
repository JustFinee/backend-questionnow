package com.backend.questionnow.service;

import com.backend.questionnow.dto.QuestionnaireDto;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.entity.User;
import com.backend.questionnow.repository.QuestionnaireRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

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

    public List<QuestionnaireDto> getAllUserQuestionnaires(Long userId) throws NotFoundException {

        User user = userService.findUserById(userId);
        List<Questionnaire> listQuestionnaire = user.getQuestionnaireList();
        return mapQuestionnaireListToQuestionnaireDtoList(listQuestionnaire);
    }

    public Questionnaire getQuestionnaireById(Long questionnaireId) throws NotFoundException {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        return questionnaire.orElseThrow(() -> new NotFoundException("Not Found questionnaire with id: " + questionnaireId));
    }

    public QuestionnaireDto getStartQuestionnaire(Long questionnaireId) throws NotFoundException {
        Questionnaire questionnaire = getQuestionnaireById(questionnaireId);
        return mapQuestionnaireToQuestionnaireDto(questionnaire);
    }

    private QuestionnaireDto mapQuestionnaireToQuestionnaireDto(Questionnaire questionnaire) throws NotFoundException {
        return new QuestionnaireDto(questionnaire.getQuestionnaireId(), questionnaire.getName(), questionnaire.getFirstQuestion());
    }

    private List<QuestionnaireDto> mapQuestionnaireListToQuestionnaireDtoList(List<Questionnaire> questionnaireList) {
        List<QuestionnaireDto> questionnaireDtoList = new ArrayList<>();
        questionnaireList.stream()
                .forEach(questionnaire -> {
                    try {
                        questionnaireDtoList.add(mapQuestionnaireToQuestionnaireDto(questionnaire));
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                });
        return questionnaireDtoList;
    }
}
