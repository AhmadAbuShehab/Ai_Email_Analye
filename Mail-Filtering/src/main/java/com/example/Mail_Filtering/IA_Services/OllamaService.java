package com.example.Mail_Filtering.IA_Services;

import com.example.Mail_Filtering.IA_Services.Http_AI.AiRequest;
import com.example.Mail_Filtering.IA_Services.Http_AI.AiResponse;
import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class OllamaService {
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private EmailContainerModel emailContainerModel;



    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    public OllamaService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public void mailSplitAiResponse(EmailModel emailModel) {
        AiRequest aiRequest = new AiRequest();
        aiRequest.setModel("gpt-4o");
        aiRequest.setPrompt("you will recive a Json Objct, which contains E-mail information " +
                "sender, reciver , subjct and body" +
                "i want you to read the body of the E-mail, it is going to contain a Transport " +
                "order Information for a logistics company, and if you noticed" +
                "that the E-mail body contains more then one order, i want you to create a Json " +
                "Object, which contains a sub-Json object for evrey single order in the E-mail body,"+
                "the sub-Json objects have to have the following structure"+
                "absender:"+ emailModel.getAbsender()+
                "empfaenger: "+emailModel.getEmpfaenger()+
                "betriff: "+ emailModel.getBetriff()+
                "body: the text of the individual order from the original E-mail" + emailModel.getBody()+" .but very important! give me the data as clean json code!!!");
        aiRequest.setStream(false);
        AiResponse aiResponse = restTemplate.postForObject(OLLAMA_URL, aiRequest,AiResponse.class);
        System.out.println(aiResponse.getResponse());

        String filePath = "C:/Users/ahmad/Desktop/Spring_boot_projects/Mail-Filtering (1)/Data/Date.json";
        try {
            Files.writeString(Path.of(filePath), aiResponse.getResponse());
            System.out.println("R");
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Schreiben der JSON-Datei: " + filePath, e);
        }


    }


    public String readJsonFromFile()  {
        String filePath = "C:/Users/ahmad/Desktop/Spring_boot_projects/Mail-Filtering (1)" +
                "/Data/Date" +
                ".json";
        String content ="";
        try {
             content = Files.readString(Path.of(filePath));
            System.out.println("JSON erfolgreich gespeichert!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
