package common.dto;

public class QuestionDTO {
    public String username;
    public String subject;
    public String question;

    public QuestionDTO(String username, String subject, String question) {
        this.username = username;
        this.subject = subject;
        this.question = question;
    }

    public QuestionDTO() {
    }
}
