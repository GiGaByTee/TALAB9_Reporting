package business;

import model.Email;
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

    public void sendLetter() {
        mainPage.clickSendButton();
        logger.info("The letter has been sent");
    }

    public Email getSavedEmail(){
        return mainPage.takeEmail();
    }
}