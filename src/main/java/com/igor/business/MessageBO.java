package com.igor.business;

import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.AlertDialogWidget;
import com.igor.page.widget.NewMessageWidget;
import com.igor.page.widget.SendingMessageDialogWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MessageBO {
    private static final Logger LOGGER = LogManager.getLogger(MessageBO.class);
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
        LOGGER.info("Opening new message widget");
        mainPage.clickToComposeButton();
        LOGGER.info("set receiver");
        newMessageWidget.setReceiverField(receiver);
        LOGGER.info("set title");
        newMessageWidget.setTitleField(topic);
        LOGGER.info("set message");
        newMessageWidget.setMessageField(message);
    }

    public void correctReceiver(String receiver){
        LOGGER.info("closing alert dialog");
        alertDialogWidget.clickToButtonOk();
        LOGGER.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        LOGGER.info("writing correct receiver");
        newMessageWidget.setReceiverField(receiver);
    }

    public void sendMessage(){
        LOGGER.info("sending message");
        newMessageWidget.clickToSendButton();
    }

    public boolean isAlertWidgetVisible(){
        LOGGER.info("checking opened alert dialog");
        return alertDialogWidget.alertDialogIsEnable();
    }

    public boolean isLetterSent(String topic){
        LOGGER.info("waiting while sending message dialog widget is active");
        sendingMessageDialogWidget.waitWhileMessageSending();
        LOGGER.info("opening sent page");
        mainPage.goToSentPage();
        LOGGER.info("checking sent page");
        return sentPage.getLetter().equals(topic);
    }
}
