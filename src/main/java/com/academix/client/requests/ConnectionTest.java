package com.academix.client.requests;

public class ConnectionTest {
    public static void main(String[] args) {
        RequesterUser requesterUser = RequesterUser.getInstance();
        RequesterAdmin requesterAdmin = RequesterAdmin.getInstance();
        RequesterFaculty requesterFaculty = RequesterFaculty.getInstance();
        RequesterStudent requesterStudent = RequesterStudent.getInstance();
        // register faculty representative
        // System.out.println(requesterUser.register("bobert14", "password", "faculty_representative"));

        // register student
        // System.out.println(requesterUser.register("shmosby", "password", "student"));

        // login
        // System.out.println(requesterUser.login("shmosby", "password"));

        // update account info /account/{username}/update?x=y... would be more fitting, but without using Spring (or something similar), this is more scalable
        // System.out.println(requesterUser.updateAccountInfo("shmosby", "email", "firstname", "lastname", "phone_number"));

        // reset password
        // System.out.println(requesterUser.resetPassword("shmosby", "password", "new_password"));
        // System.out.println(requesterUser.resetPassword("shmosby", "new_password", "password"));

        // submit question
        // System.out.println(requesterUser.sendQuestion("shmosby", "What is the meaning of life?"));

        // answer question
        // System.out.println(requesterAdmin.answerQuestion(1L, "He died for our sins"));

        // approve faculty representative
        // System.out.println(requesterAdmin.acceptRequest(2L));

        // create a faculty
        // System.out.println(requesterFaculty.createFaculty("bobert14", "STUBA", "Faculty of Oopla doopla poopy boopy oopy", "Yeah, its a school. Get over it", "Informatics", "5.0", "https://www.stuba.sk", "https://www.stuba.sk/image.jpg"));

        // get pending questions
        // System.out.println(requesterAdmin.getPendingQuestions());

        // get pending requests
        // System.out.println(requesterAdmin.getPendingRequests());

        // get all faculties
        // System.out.println(requesterUser.get_faculties());

        // get quiz
        // System.out.println(requesterStudent.getQuiz());

        // get university based on quiz
        System.out.println(requesterStudent.facultyBasedOnQuiz("informatics", "1.55"));
    }
}
