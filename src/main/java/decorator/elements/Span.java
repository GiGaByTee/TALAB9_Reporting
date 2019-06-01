package decorator.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Span extends BaseElement {
    private static Logger logger = LogManager.getLogger(Span.class);

    public Span(WebElement element) {
        super(element);
    }

    public void click(){
        if(isEnabled()) {
            element.click();
        }
    }

    public Boolean isEnabled(){
       return element.isEnabled();
    }
    public void getContent(){
         logger.info(element.getText());
    }
}
