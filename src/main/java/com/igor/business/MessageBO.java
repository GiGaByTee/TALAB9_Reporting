package com.igor.business;

import com.igor.logger.CustomLogger;
import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.AlertDialogWidget;
import com.igor.page.widget.NewMessageWidget;
import com.igor.page.widget.SendingMessageDialogWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

    public void fillFieldsForMessage(String receiver, String topic, String message){
        CustomLogger.info("Opening new message widget");
        mainPage.clickToComposeButton();
        CustomLogger.info("set receiver");
        newMessageWidget.setReceiverField(receiver);
        CustomLogger.info("set title");
        newMessageWidget.setTitleField(topic);
        CustomLogger.info("set message");
        newMessageWidget.setMessageField(message);
    }

    public void correctReceiver(String receiver){
        CustomLogger.info("closing alert dialog");
        alertDialogWidget.clickToButtonOk();
        CustomLogger.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        CustomLogger.info("writing correct receiver");
        newMessageWidget.setReceiverField(receiver);
    }

    public void sendMessage(){
        CustomLogger.info("sending message");
        newMessageWidget.clickToSendButton();
    }

    public boolean isAlertWidgetVisible(){
        CustomLogger.info("checking opened alert dialog");
        return alertDialogWidget.alertDialogIsEnable();
    }

    public boolean isLetterSent(String topic){
        CustomLogger.info("waiting while sending message dialog widget is active");
        sendingMessageDialogWidget.waitWhileMessageSending();
        CustomLogger.info("opening sent page");
        mainPage.goToSentPage();
        CustomLogger.info("checking sent page");
        return sentPage.getLetter().equals(topic);
    }
}
