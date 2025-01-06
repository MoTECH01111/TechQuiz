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
 * Question for Quiz model.
 */

public class QuestionForQuiz {  // Class for Questions

    private String questionText; //Declare the questions
    private List<String> choices; // Declare the choices
    private int correctAnswerIndex; // Declare the correct answer index

  
    public QuestionForQuiz() {
        this.choices = new ArrayList<>();  // Choices is saved to an array
    }

    //Initialize constructor objeact with the provided variables
    public QuestionForQuiz(String questionText, List<String> choices, int correctAnswerIndex) {
        this.questionText = questionText;
        this.choices = choices != null ? choices : new ArrayList<>(); //creates an array is choice is null to avoid NullPointerException
        this.correctAnswerIndex = correctAnswerIndex;
    }
    
    //This section is setters and getters 
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getChoices() {
        return choices;
    }
    
    //Add choices to the arraylist
    public void addChoice(String choice) {
        if (choices == null) {
            choices = new ArrayList<>();
        }
        choices.add(choice);
    }
    
    //creates an array is choice is null to avoid NullPointerException
    public void setChoices(List<String> choices) {
        this.choices = choices != null ? choices : new ArrayList<>();
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    //Override a string representation of the objects
    @Override
    public String toString() {
        return "QuestionForQuiz{" +
                "questionText='" + questionText + '\'' +
                ", choices=" + choices +
                ", correctAnswerIndex=" + correctAnswerIndex +
                '}';
    }
}
