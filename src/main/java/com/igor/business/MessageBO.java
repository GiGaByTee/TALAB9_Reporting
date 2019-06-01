package com.igor.business;

import com.igor.logger.AllureLogger;
import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.AlertDialogWidget;
import com.igor.page.widget.NewMessageWidget;
import com.igor.page.widget.SendingMessageDialogWidget;
import ru.yandex.qatools.allure.annotations.Step;

public class MessageBO {
    private MainPage mainPage;
    private SentPage sentPage;
    private NewMessageWidget newMessageWidget;
    private AlertDialogWidget alertDialogWidget;
    private SendingMessageDialogWidget sendingMessageDialogWidget;

    public MessageBO(){
        mainPage = new MainPage();
        sentPage = new SentPage();
        newMessageWidget = new NewMessageWidget();
        alertDialogWidget = new AlertDialogWidget();
        sendingMessageDialogWidget = new SendingMessageDialogWidget();
    }

    @Step("Fill fields for new message with receiver: {0}, topic: {1} and message: {2}. running method: {method} step")
    public void fillFieldsForMessage(String receiver, String topic, String message){
        AllureLogger.info("Opening new message widget");
        mainPage.clickToComposeButton();
        AllureLogger.info("set receiver");
        newMessageWidget.setReceiverField(receiver);
        AllureLogger.info("set title");
        newMessageWidget.setTitleField(topic);
        AllureLogger.info("set message");
        newMessageWidget.setMessageField(message);
    }

    @Step("Correct receiver with receiver: {0}. running method: {method} step")
    public void correctReceiver(String receiver){
        AllureLogger.info("closing alert dialog");
        alertDialogWidget.clickToButtonOk();
        AllureLogger.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        AllureLogger.info("writing correct receiver");
        newMessageWidget.setReceiverField(receiver);
    }

    @Step("Send message running method: {method} step")
    public void sendMessage(){
        AllureLogger.info("sending message");
        newMessageWidget.clickToSendButton();
    }

    @Step("Checking that alert dialog opened. running method: {method} step")
    public boolean isAlertWidgetVisible(){
        AllureLogger.info("checking opened alert dialog");
        return alertDialogWidget.alertDialogIsEnable();
    }

    @Step("Checking that letter with topic: {0} is in sent page. running method: {method} step")
    public boolean isLetterSent(String topic){
        AllureLogger.info("waiting while sending message dialog widget is active");
        sendingMessageDialogWidget.waitWhileMessageSending();
        AllureLogger.info("opening sent page");
        mainPage.goToSentPage();
        AllureLogger.info("checking sent page");
        return sentPage.getLetter().equals(topic);
    }
}
