package com.backend.questionnow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    Long userId;

    String name;
    String email;
    String password;
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Questionnaire> questionnaireList = new ArrayList<>();


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addQuestionnaire(Questionnaire questionnaire)
    {
        questionnaireList.add(questionnaire);
    }
}
