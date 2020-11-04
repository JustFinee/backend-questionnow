package com.backend.questionnow.controller;

import com.backend.questionnow.entity.Question;
import com.backend.questionnow.service.QuestionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @PostMapping("/createQuestion") //to bedzie rzadko uzywane --> najczesciej przesylamy cale ankiety
    public ResponseEntity createQuestion(@RequestBody Question question, @RequestParam Long questionnaireId) {
        try {
            return new ResponseEntity<>(questionService.saveQuestion(question, questionnaireId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getNextQuestion")
    public ResponseEntity getNextQuestion(@RequestParam Long questionnaireId, @RequestParam Long questionNumber)
    {
        try{
            return new ResponseEntity(questionService.getNextQuestion(questionnaireId,questionNumber),HttpStatus.OK);
        }
        catch(NotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
