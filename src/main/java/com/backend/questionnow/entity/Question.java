package com.backend.questionnow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Question")
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    Long questionId;

    String value;
    Long questionNumber;

    public Question(String value, Long questionNumber) {
        this.value = value;
        this.questionNumber = questionNumber;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Answer> answerList = new ArrayList<>();
}
