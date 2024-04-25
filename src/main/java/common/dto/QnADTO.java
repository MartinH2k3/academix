package common.dto;

public class QnADTO {
    public String question;
    public String answer;

    public QnADTO(){}

    public QnADTO(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
}
