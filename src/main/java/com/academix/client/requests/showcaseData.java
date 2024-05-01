package com.academix.client.requests;

public class showcaseData {
    public static void main(String[] args) {
        RequesterUser requesterUser = RequesterUser.getInstance();
        RequesterAdmin requesterAdmin = RequesterAdmin.getInstance();
        RequesterFaculty requesterFaculty = RequesterFaculty.getInstance();
        RequesterStudent requesterStudent = RequesterStudent.getInstance();

        System.out.println(requesterUser.register("stdnt", "password", "student"));

        // submit question
        for (Long i = 1L; i < 5L; i++) {
            requesterUser.sendQuestion("stdnt", Long.toString(i),"Is this question number " + i + "?");
        }

        System.out.println(requesterUser.register("facrep1", "password", "faculty_representative"));

        //System.out.println(requesterAdmin.acceptRequest(1L));

        // create a faculty
        System.out.println(requesterFaculty.createFaculty("facrep1", "STU", "Fakulta informatiky a informačných technológií", "Fakulta informatiky a informačných technológií Slovenskej technickej univerzity je siedmou a najmladšou fakultou Slovenskej technickej univerzity v Bratislave. ", "Informatics", "2.5", "https://www.fiit.stuba.sk/", "https://www.fiit.stuba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "STU", "Fakulta elektrotechniky a informatiky", "Fakulta elektrotechniky a informatiky Slovenskej technickej univerzity v Bratislave je najväčšou zo siedmich fakúlt Slovenskej technickej univerzity v Bratislave. Je to najstaršia technická fakulta na Slovensku zameraná na elektrotechniku, informatiku a príbuzné oblasti. ", "Informatics", "4.0", "https://www.fei.stuba.sk/", "https://www.fei.stuba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "UNIZA", "Fakulta riadenia a informatiky", "Fakulta riadenia a informatiky Žilinskej univerzity v Žiline je jednou z deviatich fakúlt Žilinskej univerzity v Žiline. ", "Informatics", "4.5", "https://www.fri.uniza.sk/", "https://www.fri.uniza.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "Univerzita Komenského", "Právnická fakulta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "law", "3.5", "https://www.flaw.uniba.sk/", "https://www.flaw.uniba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "Univerzita Komenského", "Fakulta matematiky, fyziky a informatiky", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "mathematics", "2.5", "http://www.fmph.uniba.sk/", "http://www.fmph.uniba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "Univerzita Komenského", "Fakulta sociálnych a ekonomických vied", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "economics", "4.0", "https://www.fsev.uniba.sk/", "https://www.fsev.uniba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "Univerzita Komenského", "Fakulta prírodných vied", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "biology", "2.5", "https://www.fpv.uniba.sk/", "https://www.fpv.uniba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "Univerzita Komenského", "Fakulta filozofická", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "philosophy", "3.5", "https://www.fphil.uniba.sk/", "https://www.fphil.uniba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "Univerzita Komenského", "Fakulta pedagogická", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "pedagogy", "4.0", "https://www.fpedas.uniba.sk/", "https://www.fpedas.uniba.sk/"));
        System.out.println(requesterFaculty.createFaculty("facrep1", "EUBA", "Obchodná Fakulta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "business", "2.5", "https://of.euba.sk/", "https://of.euba.sk/"));

    }
}
