/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package techquizapi.techquiz.models; //Model package 

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 30/12/2024
 * Author: Morris Ouedraogo
 * Quiz model class.
 */

public class Quiz {  //Class for Quiz

    private String quizId; //Declare for Quiz ID
    private String title;  // Declare the Title 
    private List<QuestionForQuiz> questions; // Declare the list for Questions

    //Constructor
    public Quiz() {
        this.questions = new ArrayList<>();
    }

    //Initialize constructor objeact with the provided variables
    public Quiz(String quizId, String title) {
        this.quizId = quizId;
        this.title = title;
        this.questions = new ArrayList<>();
    }

    //Setters and Getters
    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionForQuiz> getQuestions() {
        return questions;
    }

    //Adds multiple Question for quiz
    public void addQuestion(QuestionForQuiz question) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(question);
    }
    
    //Used to add new questions to the question list
    public void addQuestions(List<QuestionForQuiz> newQuestions) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.addAll(newQuestions);
    }

    public void setQuestions(List<QuestionForQuiz> questions) {
        this.questions = questions;
    }

    //Retrieve total ammount of questions
    public int getTotalQuestions() {
        return questions == null ? 0 : questions.size();
    }

    //Override a string representation of the objects
    @Override
    public String toString() {
        return "Quiz{" +
                "quizId='" + quizId + '\'' +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}
