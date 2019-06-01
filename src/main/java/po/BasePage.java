package po;
import decorator.CustomFieldDecorator;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;



public class BasePage {

    protected WebDriver driver;

    public BasePage(){
        driver = DriverManager.getDriver();
        PageFactory.initElements(new CustomFieldDecorator(driver),this);
    }

}
