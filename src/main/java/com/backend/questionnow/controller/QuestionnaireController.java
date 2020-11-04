package com.backend.questionnow.controller;

import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.service.QuestionnaireService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/createQuestionnaire")
    public Questionnaire createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.saveQuestionnaire(questionnaire);
    }

    @GetMapping("/getAllQuestionnaires")
    public Iterable<Questionnaire> getAllQuestionnaires() {
        return questionnaireService.getAllQuestionnaires();
    }

    @GetMapping("/getStartQuestionnaire")
    public ResponseEntity getStartQuestionnaire(@RequestParam Long questionnaireId) {
        try {
            return new ResponseEntity(questionnaireService.getStartQuestionnaire(questionnaireId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
