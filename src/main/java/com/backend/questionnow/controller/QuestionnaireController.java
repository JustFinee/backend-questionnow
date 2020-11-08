package com.backend.questionnow.controller;

import com.backend.questionnow.entity.Questionnaire;
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
    public ResponseEntity getAllQuestionnaires(@RequestParam Long userId)
    {
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //System.out.println(authentication.getDetails());
        //String currentPrincipalName = authentication.
        //System.out.println(currentPrincipalName);

        try{
            return new ResponseEntity(questionnaireService.getAllUserQuestionnaires(userId),HttpStatus.OK);
        } catch (NotFoundException e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

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
