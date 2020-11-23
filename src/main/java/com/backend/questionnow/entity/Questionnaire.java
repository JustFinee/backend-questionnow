package com.backend.questionnow.entity;

import com.backend.questionnow.security.CustomException;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Questionnaire")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questionnaire")
public class Questionnaire {

    @Id
    @GeneratedValue
    Long questionnaireId;

    String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Question> questionList = new ArrayList<>();

    String unicKey = UUID.randomUUID().toString();

    public Questionnaire(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public Question getFirstQuestion() throws CustomException {
        return questionList.stream()
                .findFirst()
                .orElseThrow(() -> new CustomException("NoQuestionsException","There is no questions in questionnaire with id: "+this.questionnaireId, HttpStatus.NOT_FOUND));
    }
}
