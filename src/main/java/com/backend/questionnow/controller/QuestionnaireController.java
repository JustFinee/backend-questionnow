package com.backend.questionnow.controller;

import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.security.CustomException;
import com.backend.questionnow.service.QuestionnaireService;
import com.backend.questionnow.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    UserService userService;

    @PostMapping("/createQuestionnaire")
    public Questionnaire createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.saveQuestionnaire(questionnaire);
    }


    @GetMapping("/getAllUserQuestionnaires")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity getAllQuestionnaires(@RequestParam Long userId) {
        try {
            return new ResponseEntity(questionnaireService.getAllUserQuestionnaires(userId), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(e.getName()+" "+e.getMessage(), e.getHttpStatus());
        }

    }

    @GetMapping("/getStartQuestionnaire")
    public ResponseEntity getStartQuestionnaire(@RequestParam Long questionnaireId) {
        try {
            return new ResponseEntity(questionnaireService.getStartQuestionnaire(questionnaireId), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(e.getName()+" "+e.getMessage(), e.getHttpStatus());
        }

    }

}
