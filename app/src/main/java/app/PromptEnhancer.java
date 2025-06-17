package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;



public class PromptEnhancer{
    private static final String API_URL ="https://api-inference.huggingface.co/models/mistralai/Mixtral-8x7B-Instruct-v0.1";


    private static String API_KEY;

    static {
        try {
            Scanner scanner = new Scanner(new File("api-key.txt"));
            API_KEY = scanner.nextLine().trim();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("apikey.txt not found!");
            API_KEY = "";
        }
    }

    public static String enhancePrompt(String prompt){
        try {                                            
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            
        
            String requestBody = "{\"inputs\":\"" + prompt + "\"}";





            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                responseBuilder.append(line);
            }

        


            in.close();

            String raw = responseBuilder.toString();
            String extracted = raw.replaceAll(".*\"generated_text\":\"", "").replaceAll("\".*", "");

            return extracted;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}







