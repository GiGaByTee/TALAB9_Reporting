package business;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pages.MainPage;

public class MainPageBO {
    MainPage mainPage = new MainPage();
    private static final Logger logger = LogManager.getLogger(LoginPageBO.class);

    public void composeEmail(String toWhom, String subject, String textMessage) {
        mainPage.clickComposeButton();
        mainPage.typeWhomField(toWhom);
        mainPage.typeSubjectField(subject);
        mainPage.typeTextField(textMessage);
        mainPage.clickOnCloseMessageButton();
        logger.info("The draft was created");
    }

    public void openSavedDraft() {
        mainPage.openListOfDrafts();
        mainPage.clickOnLastMessageInDraftFolder();
        logger.info("The draft was open");
    }

    public boolean isSavedEmailInDraftEqualsToEntered(String enteredEmail) {
        String savedEmail = mainPage.takeEmailAddress();
        return savedEmail.equals(enteredEmail);
    }

    public boolean isSavedSubjectEqualsToEntered(String enteredSubject) {
        String savedSubject = mainPage.takeLetterSubject();
        return savedSubject.equals(enteredSubject);
    }

    public boolean isSavedLetterTextEqualsToEntered(String enteredText) {
        String savedText = mainPage.takeLetterText();
        return savedText.equals(enteredText);
    }

    public void sendLetter() {
        mainPage.clickSendButton();
        logger.info("The letter has been sent");
    }
}