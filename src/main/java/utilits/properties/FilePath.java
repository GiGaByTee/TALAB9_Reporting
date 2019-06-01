package utilits.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class FilePath {
    private Properties properties = new Properties();
    private FileInputStream inputStream = null;
    private String filePath = null;
    private Logger logger = LogManager.getLogger(FilePath.class);

    public String propertyFile(String keyToFile){
        try{
            inputStream = new FileInputStream("src/test/java/resources/path/path.properties");
            properties.load(inputStream);
            filePath = properties.getProperty(keyToFile);
        }catch (IOException e){
            logger.error(e.getClass());
            e.printStackTrace();
        }finally {
            if(!Objects.isNull(inputStream)){
                try{
                    inputStream.close();
                }catch (IOException e){
                    logger.error(e.getClass());
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }
}
