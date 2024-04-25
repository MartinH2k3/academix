package common.dto;

public class QnADTO {
    public String question;
    public String questionSubject;
    public String answer;

    public QnADTO(){}

    public QnADTO(String question, String questionSubject, String answer){
        this.question = question;
        this.questionSubject = questionSubject;
        this.answer = answer;
    }
}
