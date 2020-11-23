package com.backend.questionnow.repository;

import com.backend.questionnow.entity.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire,Long> {

    Questionnaire findByUnicKey(String unicKey);
}
