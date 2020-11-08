package com.backend.questionnow;

import com.backend.questionnow.entity.Answer;
import com.backend.questionnow.entity.Question;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.entity.User;
import com.backend.questionnow.repository.QuestionnaireRepository;
import com.backend.questionnow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {
        Questionnaire questionnaire = new Questionnaire("Ankieta 1");
        Questionnaire questionnaire1 = new Questionnaire("Ankieta 2");
        Question question1 = new Question("Pytanie 1",1L);
        Question question2 = new Question("Pytanie 2",2L);
        Question question3 = new Question("Pytanie 3",3L);
        Question question4 = new Question("Pytanie 4",1L);
        Answer answer1 = new Answer("Tak",1L,2L);
        Answer answer2 = new Answer("Nie",2L,3L);
        Answer answer3 = new Answer("Tak",1L,null);
        Answer answer4 = new Answer("Nie",2L,null);
        Answer answer5 = new Answer("Tak",1L,null);
        Answer answer6 = new Answer("Nie",2L,null);
        Answer answer7 = new Answer("Nie",2L,null);

        question1.getAnswerList().add(answer1);
        question1.getAnswerList().add(answer2);
        question2.getAnswerList().add(answer3);
        question2.getAnswerList().add(answer4);
        question3.getAnswerList().add(answer5);
        question3.getAnswerList().add(answer6);
        question4.getAnswerList().add(answer7);

        questionnaire.addQuestion(question1);
        questionnaire.addQuestion(question2);
        questionnaire.addQuestion(question3);
        questionnaire1.addQuestion(question4);

        User user = new User("Bartek","mail", passwordEncoder.encode("123"));
        user.addQuestionnaire(questionnaire);
        user.addQuestionnaire(questionnaire1);
        userRepository.save(user);

        }
    }
