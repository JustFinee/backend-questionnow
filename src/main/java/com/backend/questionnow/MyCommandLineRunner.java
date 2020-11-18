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
    UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {
        Questionnaire questionnaire = new Questionnaire("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do");
        Questionnaire questionnaire1 = new Questionnaire("Ankieta 2");
        Questionnaire questionnaire3 = new Questionnaire("Ankieta 3");
        Questionnaire questionnaire4 = new Questionnaire("Ankieta 4");
        Questionnaire questionnaire5 = new Questionnaire("Ankieta 5");
        Question question1 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1L);
        Question question2 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 2",2L);
        Question question3 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 3",3L);
        Question question4 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1L);
        Question question5 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",1L);
        Question question6 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 4",1L);
        Question question7 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 4",1L);
        Question question8 = new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 4",1L);
        Answer answer1 = new Answer("Lorem ipsum dolor sit amet",1L,2L);
        Answer answer2 = new Answer("Lorm dolor sit amet",2L,3L);
        Answer answer3 = new Answer("Lorem ipsum dolor sit amet",1L,null);
        Answer answer4 = new Answer("Lorem ipsum dolor sit amet",2L,null);
        Answer answer5 = new Answer("Loamet",1L,null);
        Answer answer6 = new Answer("Lorem ipsum dolor sit amet",2L,null);
        Answer answer7 = new Answer("Nirem ipsum dolor sit e",2L,null);
        Answer answer8 = new Answer("Nvrem ipsum dolor sit ie",2L,null);
        Answer answer9 = new Answer("Nie",2L,null);
        Answer answer10 = new Answer("Nie",2L,null);
        Answer answer11 = new Answer("Nie",2L,null);

        question1.getAnswerList().add(answer1);
        question1.getAnswerList().add(answer2);
        question2.getAnswerList().add(answer3);
        question2.getAnswerList().add(answer4);
        question3.getAnswerList().add(answer5);
        question3.getAnswerList().add(answer6);
        question4.getAnswerList().add(answer7);
        question5.getAnswerList().add(answer8);
        question6.getAnswerList().add(answer9);
        question7.getAnswerList().add(answer10);
        question8.getAnswerList().add(answer11);

        questionnaire.addQuestion(question1);
        questionnaire.addQuestion(question2);
        questionnaire.addQuestion(question3);
        questionnaire1.addQuestion(question5);
        questionnaire3.addQuestion(question6);
        questionnaire4.addQuestion(question7);
        questionnaire5.addQuestion(question8);

        User user = new User("Bartek","bartek", passwordEncoder.encode("123"));
        User user1 = new User("Ania","ania", passwordEncoder.encode("123"));
        user.addQuestionnaire(questionnaire);
        user.addQuestionnaire(questionnaire1);
        user.addQuestionnaire(questionnaire3);
        user.addQuestionnaire(questionnaire4);
        user.addQuestionnaire(questionnaire5);
        userRepository.save(user);
        userRepository.save(user1);

        }
    }
