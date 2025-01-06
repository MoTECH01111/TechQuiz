/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package techquizapi.techquiz.service; //Service package

import java.util.List;
import techquizapi.techquiz.models.Quiz;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Date: 30/12/2024
 * @author morrisouedraogo
 * Services
 */


public class QuServices {
    private static final ConcurrentHashMap<String, Quiz> quizzes = new ConcurrentHashMap<>(); //Declare a concurrentHashMap
    
    //Methods to Get quizzes
    public static ConcurrentHashMap<String, Quiz> getQuizzes() {
          return quizzes;
    }

    //Method to add quiz to the arraylist and ensure no duplicates
    public static boolean addQuiz(Quiz quiz) {
        if (quizzes.containsKey(quiz.getQuizId())) {
            return false;
        }
        quizzes.put(quiz.getQuizId(), quiz);
        return true;
    }
    
    //Method to get Quiz by ID 
    public static Quiz getQuizById(String quizId) {
        return quizzes.get(quizId);
    }
    
    //Method to get Delete Quiz by ID 
    public static boolean deleteQuiz(String quizId) {
        return quizzes.remove(quizId) != null;
    }
    //Return the list of all quizzes
    public static List<Quiz> getAllQuizzes() {
        return List.copyOf(quizzes.values());
    }
    
}