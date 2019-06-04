package data;

import org.testng.annotations.DataProvider;
import utils.CSVParser;

import java.util.Iterator;

public class TestDataProvider {
    @DataProvider(name = "users", parallel = true)
    public Iterator<Object[]> users() {
            return CSVParser.parseCSV(Consts.PATH_TO_USERS_FILE).iterator();
    }

    @DataProvider(name = "recipientsMails", parallel = true)
    public Iterator<Object[]> recipientsTest() {
        return CSVParser.parseCSV(Consts.PATH_TO_RECIPIENTS_EMAILS_FILE).iterator();
    }

}
