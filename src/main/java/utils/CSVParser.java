package utils;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<Object[]> parseCSV(String pathToCSVFile){
        List<Object[]> dataList = new ArrayList<>();
        CSVReader reader = null;
        try
        {
            reader = new CSVReader(new FileReader(new File(pathToCSVFile)),',');
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null)
            {
                dataList.add(nextLine);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

}
