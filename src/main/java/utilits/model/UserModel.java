package utilits.model;

public class UserModel {
    private String login;
    private String password;
    private MessageModel messageModel;


  public UserModel(String login, String password, MessageModel messageModel){
        this.login = login;
        this.password = password;
        this.messageModel = messageModel;

    }
    public UserModel(){}

    public MessageModel getMessageModel() {
        return messageModel;
    }

    public void setMessageModel(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
