import java.io.IOException;


public class HttpServerExample {

    public static void main(String[] args){
        System.out.println("Server Starting ...");
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.loadHttpConfigurationFile("src/main/resources/httpConfig.json");
        HttpConfiguration httpConf = configManager.getCurrentHttpConfiguration();
        System.out.println(httpConf.getPort());
        System.out.println(httpConf.getWebroot());
        configManager.loadDatabaseConfiguration("src/main/resources/databaseConfig.json");
        DatabaseConfiguration databaseConf = configManager.getCurrentDatabaseConfiguration();
        System.out.println(databaseConf.getDbname());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(httpConf.getPort(), httpConf.getWebroot());
            serverListenerThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
