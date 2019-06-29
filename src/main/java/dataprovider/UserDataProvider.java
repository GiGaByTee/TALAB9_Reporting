package dataprovider;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.List;
import java.util.Map;

import static constants.Constants.PATH_TO_USER_DATA;

public class UserDataProvider {


    @DataProvider(name = "json-data")
    public static Object[][] getJsonData() throws IOException {
        File file = new File(PATH_TO_USER_DATA);
        JsonArray array = new JsonParser().parse(new FileReader(file))
                .getAsJsonArray();
        Gson gson = new Gson();
        List<Map> list = gson.fromJson(array, List.class);
        Object[][] objects = list.stream()
                .map(testData ->
                        testData.values().stream().toArray()).toArray(Object[][]::new);
        return objects;
    }
}
