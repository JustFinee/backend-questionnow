package com.backend.questionnow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Answer")
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue
    Long answerId;
    String value;
    Long answerNumber;
    Long nextQuestionNumber;
    int counter=0;

    public Answer(String value, Long answerNumber, Long nextQuestionNumber, int counter) {
        this.value = value;
        this.answerNumber = answerNumber;
        this.nextQuestionNumber = nextQuestionNumber;
        this.counter = counter;
    }
}
