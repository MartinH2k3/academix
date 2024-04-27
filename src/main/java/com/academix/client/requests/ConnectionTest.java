package com.academix.client.requests;

import common.dto.QnADTO;

import java.util.List;

public class ConnectionTest {
    public static void main(String[] args) {
        RequesterUser requesterUser = RequesterUser.getInstance();
        RequesterAdmin requesterAdmin = RequesterAdmin.getInstance();
        RequesterFaculty requesterFaculty = RequesterFaculty.getInstance();
        RequesterStudent requesterStudent = RequesterStudent.getInstance();
        // register faculty representative
        //System.out.println(requesterUser.register("facrep1", "password", "faculty_representative"));

        // register student
        System.out.println(requesterUser.register("student1", "password", "student"));

        // login
        //System.out.println(requesterUser.login("student1", "password"));

        // update account info (email, firstname, lastname, phone_number)
        //System.out.println(requesterUser.updateAccountInfo("student1", "bob", "Stude bt", "Studen tovski", "bob"));

        // get account info
        //System.out.println(requesterUser.getAccountInfo("student1"));
        // reset password
        // System.out.println(requesterUser.resetPassword("student1", "password", "new_password"));

        // submit question
        //System.out.println(requesterUser.sendQuestion("student1", "Meaning", "What is the meaning of life?"));
//        for (Long i = 1L; i < 5L; i++) {
//            requesterUser.sendQuestion("student1", Long.toString(i),"What is the meaning of life?");
//            requesterAdmin.answerQuestion(i, Long.toString(i));
//        }
        // answer question
        // System.out.println(requesterAdmin.answerQuestion(1L, "potatoes"));

        // get answers for requests
//        List<QnADTO> answers = requesterUser.getResponses("student1");
//        if (answers != null) {
//            for (QnADTO answer : answers) {
//                System.out.println(answer.question + ":" + answer.questionSubject + ":" + answer.answer);
//            }
//        }
        // approve faculty representative
        //System.out.println(requesterAdmin.acceptRequest(1L));

        // create a faculty
        //System.out.println(requesterFaculty.createFaculty("facrep1", "STUBA", "Faculty of Oopla doopla poopy boopy oopy", "Yeah, its a school. Get over it", "Informatics", "5.0", "https://www.stuba.sk", "https://www.stuba.sk/image.jpg"));
        //System.out.println(requesterFaculty.createFaculty("facrep1", "STUBA", "Faculty of Oopla doopla poopy boopy oopy", "Chicken", "Informatics", "5.0", "https://www.stuba.sk", "https://www.stuba.sk/image.jpg"));

        // get pending questions
        // System.out.println(requesterAdmin.getPendingQuestions());

        // get pending requests
        // System.out.println(requesterAdmin.getPendingRequests());

        // get all faculties
        //System.out.println(requesterUser.get_faculties("Oopla", 1, 10));

        // get all users
        //var bob = requesterAdmin.showAllUsers();
        //for (String user : bob) {
        //    System.out.println(user);
        //}
        // get quiz
        // System.out.println(requesterStudent.getQuiz());

        // get university based on quiz
        // System.out.println(requesterStudent.facultyBasedOnQuiz("informatics", "1.55"));

        // delete account
//        System.out.println(requesterUser.login("pomaranc", "pomaranc"));
//        System.out.println(requesterUser.deleteAccount("pomaranc"));
//        System.out.println(requesterUser.login("pomaranc", "pomaranc"));
    }
}
