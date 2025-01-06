/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package techquizapi.techquiz.controller; //Package for Controller

/**
 * Date: 30/12/2024
 * @author morrisouedraogo
 *  Quiz Controller
 */

import techquizapi.techquiz.models.*; //Import the Model package and files
import techquizapi.techquiz.service.QuServices; //Import the service package and the QuServices file

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response; //Response Utilities 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


// Base Path for Quiz endpoints 
@Path("/quizzes")
public class QuizControll {

    private static final String TEACHER_API_KEY = "teacher-api-key"; //Declare the Teacher API key
    private static final Logger LOGGER = Logger.getLogger(QuizControll.class.getName()); //Logs activity in the web application

    //Validates the API key
    private boolean validateApiKey(String apiKey) {
        return TEACHER_API_KEY.equals(apiKey);
    }

    //Creates a new quiz 
    @POST
    @Consumes("application/json")
    public Response createQuiz(@HeaderParam("API-Key") String apiKey, Quiz quiz) {
        //Returns 403 if API Key is wrong
        if (!validateApiKey(apiKey)) {
            return Response.status(403).entity("Invalid API Key").build();
        }
        //Returns 400 if quiz already exist
        if (QuServices.getQuizzes().containsKey(quiz.getQuizId())) {
            return Response.status(400).entity("A quiz with this ID already exists").build();
        }
        //Adds the quiz to the services
        QuServices.getQuizzes().put(quiz.getQuizId(), quiz);
        LOGGER.log(Level.INFO, "Quiz created: {0}", quiz.getQuizId());
        return Response.status(201).entity("Quiz created successfully").build();
    }

    //Add a question to an existing quiz
    @PUT
    @Path("/{quizId}/questions")
    @Consumes("application/json")
    public Response addQuestion(@HeaderParam("API-Key") String apiKey, @PathParam("quizId") String quizId, QuestionForQuiz question) {
        //Returns 403 if API Key is wrong
        if (!validateApiKey(apiKey)) {
            return Response.status(403).entity("Invalid API Key").build();
        }
        
        Quiz quiz = QuServices.getQuizzes().get(quizId);
        //Returns 404 if Quiz is not found
        if (quiz == null) {
            return Response.status(404).entity("Quiz not found").build();
        }
        // Add the question to the quiz 
        quiz.getQuestions().add(question);
        LOGGER.log(Level.INFO, "Question added to quiz: {0}", quizId); //Logs the action
        return Response.status(200).entity("Question added successfully").build();
    }

    //Retrieve quiz by Id  and display quiz details
    @GET
    @Path("/{quizId}")
    @Produces("application/json")
    public Response getQuizDetails(@PathParam("quizId") String quizId) {
        Quiz quiz = QuServices.getQuizzes().get(quizId);
        if (quiz == null) {
            return Response.status(404).entity("Quiz not found").build();
        }
        //Returns and logs the quiz detail
        LOGGER.log(Level.INFO, "Quiz details retrieved for: {0}", quizId);
        return Response.ok(quiz).build();
    }

    //Deleting a quiz by its Quiz Id
    @DELETE
    @Path("/{quizId}")
    public Response deleteQuiz(@HeaderParam("API-Key") String apiKey, @PathParam("quizId") String quizId) {
        if (!validateApiKey(apiKey)) {
            return Response.status(403).entity("Invalid API Key").build();
        }

        Quiz quiz = QuServices.getQuizzes().remove(quizId);
        if (quiz == null) {
            return Response.status(404).entity("Quiz not found").build();
        }
        //Confirms the quiz being deleted
        LOGGER.log(Level.INFO, "Quiz deleted: {0}", quizId);
        return Response.status(200).entity("Quiz deleted successfully").build();
    }
    
    
    //Retrieves all available Quizzes 
    @GET
    @Path("/available")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getAvailableQuizzes() {
        LOGGER.info("Retrieved list of available quizzes");
        return Response.ok(QuServices.getQuizzes().values()).build();
    }

    //Retrieves quiz without answers on display
    @POST
    @Path("/{quizId}/take")
    @Consumes("application/json")
    @Produces("application/json")
    public Response takeQuiz(@PathParam("quizId") String quizId) {
        Quiz quiz = QuServices.getQuizzes().get(quizId);
        if (quiz == null) {
            return Response.status(404).entity("Quiz not found").build();
    }

    // Creates a copy of the questions without showing the correct answers
    List<Map<String, Object>> quizQuestions = quiz.getQuestions().stream().map(question -> {
        Map<String, Object> questionData = new HashMap<>();
        questionData.put("questionText", question.getQuestionText());
        questionData.put("choices", question.getChoices());
        return questionData;
    }).toList();
    
        //Logs and retrieves quiz with no answers.
        LOGGER.log(Level.INFO, "Quiz questions retrieved for: {0}", quizId);
        return Response.ok(quizQuestions).build();
    
    }
    
    //Submit answers for a quiz by quiz ID  and get feedback.
    @POST
    @Path("/{quizId}/submit")
    @Consumes("application/json")
    @Produces("application/json")
    public Response submitQuizAnswers(@PathParam("quizId") String quizId, List<Integer> answers) {
       //Returns 404 if Quiz is not found
        Quiz quiz = QuServices.getQuizzes().get(quizId);
        if (quiz == null) {
            return Response.status(404).entity("Quiz not found").build();
        }

        List<QuestionForQuiz> questions = quiz.getQuestions();
        if (questions.size() != answers.size()) {
            return Response.status(400).entity("Mismatch in the number of answers submitted").build();
        }

        int score = 0;
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            QuestionForQuiz question = questions.get(i);
            if (question.getCorrectAnswerIndex() == answers.get(i)) {
                score++;
                feedback.append("Question ").append(i + 1).append(": Correct\n");
            } else {
                feedback.append("Question ").append(i + 1).append(": Incorrect. Correct answer is option ")
                        .append(question.getCorrectAnswerIndex() + 1).append("\n");
            }
        }
        //Logs submitted answers and return the results with feedback
        LOGGER.log(Level.INFO, "Quiz submitted for: {0}, Score: {1}", new Object[]{quizId, score});
        return Response.ok("You Scored: " + score + "/" + questions.size() + "\nFeedback:\n" + feedback).build();
    }
}
