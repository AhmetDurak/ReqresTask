package Reqres.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

        public static final String URL;
        public static final String BASE_URL;

        public static final String USERNAME;
        public static final String PASSWORD;


        static {

            Properties properties = null;
            String environment = System.getProperty("environment") != null ?
                    System.getProperty("environment") : ConfigurationReader.get("environment");

            try {

                String path = System.getProperty("user.dir") + "/src/test/resources/environments/" + environment + ".properties";

                FileInputStream input = new FileInputStream(path);
                properties = new Properties();
                properties.load(input);
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            URL = properties.getProperty("url");
            BASE_URL = properties.getProperty("base_url");

            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");



        }

}
