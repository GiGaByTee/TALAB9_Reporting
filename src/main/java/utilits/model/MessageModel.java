package utilits.model;

public class MessageModel {
    private String receiver;
    private String subject;
    private String message;

    MessageModel(String receiver, String subject, String message){
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }
    MessageModel(){}

    public String getMessage() {
        return message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
