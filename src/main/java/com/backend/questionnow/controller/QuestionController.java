package com.backend.questionnow.controller;

import com.backend.questionnow.entity.Question;
import com.backend.questionnow.security.CustomException;
import com.backend.questionnow.service.QuestionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @PostMapping("/createQuestion") //to bedzie rzadko uzywane --> najczesciej przesylamy cale ankiety
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity createQuestion(@RequestBody Question question, @RequestParam Long questionnaireId, @RequestParam Long userId) {
        try {
            return new ResponseEntity<>(questionService.saveQuestion(question, questionnaireId), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("/getNextQuestion")
    public ResponseEntity getNextQuestion(@RequestParam Long questionnaireId, @RequestParam Long questionNumber, @RequestParam Long answerNumber, @RequestParam Long questionId)
    {
        try{
            return new ResponseEntity(questionService.getNextQuestion(questionnaireId,questionNumber,answerNumber, questionId),HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getName()+" "+e.getMessage(),e.getHttpStatus());
        }
    }
}
