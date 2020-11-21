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

import java.time.LocalDateTime;

@RestController
public class QuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    UserService userService;


    @GetMapping("/getAllUserQuestionnaires")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity getAllQuestionnaires(@RequestParam Long userId) {
        try {
            return new ResponseEntity(questionnaireService.getAllUserQuestionnaires(userId), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(e.getName()+" "+e.getMessage(), e.getHttpStatus());
        }

    }

    @GetMapping("/getUserQuestionnaire")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity getUserQuestionnaire(@RequestParam Long userId, @RequestParam Long questionnaireId) {
        try {
            return new ResponseEntity(questionnaireService.getUserQuestionnaire(questionnaireId), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
        }

    }

    @PostMapping("/saveQuestionnaire")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity saveQuestionnaire(@RequestParam Long userId, @RequestBody Questionnaire questionnaire)
    {
        try {
            return new ResponseEntity(questionnaireService.createQuestionnaire(questionnaire, userId), HttpStatus.CREATED);
        } catch (CustomException e) {
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
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

    @PutMapping("/changeQuestionnaire")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity changeTittleQuestionnaire(@RequestBody Questionnaire questionnaire,@RequestParam Long userId) {
        try {
            return new ResponseEntity(questionnaireService.changeQuestionnaire(questionnaire), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
        }

    }

    @DeleteMapping("/deleteQuestionnaire")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity deleteQuestionnaire(@RequestParam Long userId, @RequestParam Long questionnaireId){
        try{
            questionnaireService.deleteQuestionnaire(questionnaireId,userId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (CustomException e) {
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
        }
    }


}
