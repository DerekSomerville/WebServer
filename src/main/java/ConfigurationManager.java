import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
#Finished Imports

public class ConfigurationManager {

    private static ConfigurationManager configurationManager;
    private static HttpConfiguration currentHttpConfiguration;
    private static DatabaseConfiguration currentDatabaseConfiguration;

    private ConfigurationManager() {
    }

    public static  ConfigurationManager getInstance(){
        if (configurationManager == null){
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    private FileReader getFileReader(String filePath){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException ex) {
            throw new HttpConfigurationException();
        }
        return fileReader;
    }

    private StringBuffer getStringBuffer(FileReader fileReader){
        StringBuffer stringBuffer = new StringBuffer();
        int counter;
        try {
            while ((counter = fileReader.read()) != -1){
                stringBuffer.append((char)counter);
            }
        } catch (IOException ex) {
            throw new HttpConfigurationException();
        }
        return stringBuffer;
    }

    private String getJsonString(String filePath){
        FileReader fileReader = getFileReader(filePath);
        return getStringBuffer(fileReader).toString();
    }

    protected JsonNode getJsonNode(String jsonString){
        JsonNode jsonNode = null;
        try {
            jsonNode = Json.parse(jsonString);
        } catch (IOException ex) {
            throw new HttpConfigurationException("Error parsing the Configuration File",ex);
        }
        return jsonNode;
    }

    protected void convertJsonHttpToHttpConfig(JsonNode httpConfig){
        try {
            currentHttpConfiguration = Json.fromJson(httpConfig, HttpConfiguration.class);
        } catch (JsonProcessingException ex) {
            throw new HttpConfigurationException("Error parsing the Configuration File internal", ex);
        }
    }

    protected void convertJsonToDatabaseConfig(JsonNode databaseConfig){
        try {
            currentDatabaseConfiguration = Json.fromJson(databaseConfig, DatabaseConfiguration.class);
        } catch (JsonProcessingException ex) {
            throw new HttpConfigurationException("Error parsing the Configuration File internal", ex);
        }
    }

    public void loadHttpConfigurationFile(String filePath) {
        String jsonString = getJsonString(filePath);
        JsonNode httpConfig = getJsonNode(jsonString);
        convertJsonHttpToHttpConfig(httpConfig);
    }

    public void loadDatabaseConfiguration(String filePath){
        String jsonString = getJsonString(filePath);
        JsonNode databaseConfig = getJsonNode(jsonString);
        convertJsonToDatabaseConfig(databaseConfig);
    }

    public HttpConfiguration getCurrentHttpConfiguration(){
        if (currentHttpConfiguration == null){
            throw new HttpConfigurationException("No current configuration set.");
        }
        return currentHttpConfiguration;
    }

    public DatabaseConfiguration getCurrentDatabaseConfiguration(){
        if (currentDatabaseConfiguration == null){
            throw new HttpConfigurationException("No current database configuration set.");
        }
        return currentDatabaseConfiguration;
    }
}
