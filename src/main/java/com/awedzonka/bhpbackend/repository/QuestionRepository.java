package com.awedzonka.bhpbackend.repository;



import com.awedzonka.bhpbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionByQuestionContaining(String search);

}
