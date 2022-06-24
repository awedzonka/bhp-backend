package com.awedzonka.bhpbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
public class Question {

    private static final String MESSAGE_NOT_BLANK = "Pole nie może być puste";
    private static final String MESSAGE_SIZE = "Treść pytania nie może mieć więcej niż 300 znaków";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false, columnDefinition = "VARCHAR(300) NOT NULL")
    @NotEmpty(message = MESSAGE_NOT_BLANK)
    @Size(max = 300, message = MESSAGE_SIZE)
    private String question;

    @Column(length = 300, nullable = false, columnDefinition = "VARCHAR(300) NOT NULL")
    @NotEmpty(message = MESSAGE_NOT_BLANK)
    @Size(max = 300, message = MESSAGE_SIZE)
    private String answer1;

    @Column(length = 300, nullable = false, columnDefinition = "VARCHAR(300) NOT NULL")
    @NotEmpty(message = MESSAGE_NOT_BLANK)
    @Size(max = 300, message = MESSAGE_SIZE)
    private String answer2;

    @Column(length = 300, nullable = false, columnDefinition = "VARCHAR(300) NOT NULL")
    @NotEmpty(message = MESSAGE_NOT_BLANK)
    @Size(max = 300, message = MESSAGE_SIZE)
    private String answer3;

    @Column(length = 300, nullable = false, columnDefinition = "VARCHAR(300) NOT NULL")
    @NotEmpty(message = MESSAGE_NOT_BLANK)
    @Size(max = 300, message = MESSAGE_SIZE)
    private String answer4;

    @Column(length = 1, nullable = false, columnDefinition = "VARCHAR(1) NOT NULL")
    @NotEmpty(message = "Wybierz odpowiedź")
    @NotNull(message = "Wybierz odpowiedź")
    @Size(max = 1, message = "Wybierz jedną odpowiedź")
    private String good_answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getGood_answer() {
        return good_answer;
    }

    public void setGood_answer(String good_answer) {
        this.good_answer = good_answer;
    }
}
