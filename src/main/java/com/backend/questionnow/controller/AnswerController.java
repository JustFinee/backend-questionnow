package com.backend.questionnow.controller;

import com.backend.questionnow.entity.Answer;
import com.backend.questionnow.security.CustomException;
import com.backend.questionnow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/addAnswer")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity addAnswer(@RequestParam Long userId,@RequestParam Long questionnaireId, @RequestParam Long questionId, @RequestBody Answer answer)
    {
        try {
            return new ResponseEntity(answerService.addAnswer(questionnaireId,questionId,answer), HttpStatus.CREATED);
        }
        catch (CustomException e)
        {
            return new ResponseEntity(e.getMessage(),e.getHttpStatus());
        }
    }

    @PutMapping("/addCounter")
    public ResponseEntity addCounter(@RequestParam Long questionId, @RequestParam Long answerNumber)
    {
        try {
            answerService.addCounter(questionId,answerNumber);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (CustomException e)
        {
            return new ResponseEntity(e.getMessage(),e.getHttpStatus());
        }
    }
}
