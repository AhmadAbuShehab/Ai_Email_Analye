package com.example.Mail_Filtering.IA_Services;

import com.example.Mail_Filtering.IA_Services.Http_AI.AiRequest;
import com.example.Mail_Filtering.IA_Services.Http_AI.AiResponse;
import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;
import com.example.Mail_Filtering.Models.EmailOutputModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class OllamaService {
    @Autowired
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";


    public OllamaService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public void mailSplitAiResponse(EmailInputModel emailInputModel) {
        String prompt = "AUFGABE:\n" +
                "Du erhältst ein JSON-Objekt „email“. Analysiere ausschließlich das Feld \"body\".\n" +
                "Im Body stehen Transportaufträge in natürlicher Sprache.\n" +
                "\n" +
                "Für jeden Auftrag erstelle ein JSON-Objekt nach folgendem Schema, und für den " +
                "absender,empfenger und betriff über nimmst du die Werte aus dem " +
                "orginallen text:"+
                "absender:"+ emailInputModel.getAbsender()+
                "empfaenger: "+emailInputModel.getEmpfaenger()+
                "betriff: "+ emailInputModel.getBetriff()+
                "body: {" +
                "abholort: " +
                "abholzeit: " +
                "zustellort: "+
                "zustellzeit:"+
                "}"+
                "Gib das Ergebnis NUR als folgendes JSON zurück:\n" +
                "\n" +
                "{\n" +
                "  \"orders\": [ ... ]\n" +
                "}\n" +
                "\n" +
                "Falls Informationen im Text fehlen, lasse die Felder leer, aber behalte die Struktur bei.\n" +
                "\n" +
                "KEIN zusätzlicher Text.\n" +
                "KEINE Kommentare.\n" +
                "KEINE Erklärungen."+
                "Hier ist die Email:\n" +
                "\n" +
                "{{"+emailInputModel.getBody() +"}}";
        String model = "llama3";
        AiRequest aiRequest = new AiRequest(model, prompt, false);
        AiResponse aiResponse = restTemplate.postForObject(OLLAMA_URL, aiRequest,AiResponse.class);
        assert aiResponse != null;//hier muss noch der Exception empfangen
        System.out.println(aiResponse.getResponse());

        String filePath = "C:/Users/ahmad/Desktop/Spring_boot_projects/Mail-Filtering (1)" +
                "/Mail-Filtering/src/main/resources/Date.json";
        try {
            Files.writeString(Path.of(filePath), aiResponse.getResponse());
            System.out.println("JSON erfolgreich gespeichert!");
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Schreiben der JSON-Datei: " + filePath, e);
        }
    }


    public EmailContainerModel readJsonFromFile()  {
        EmailContainerModel containerModel = null;
        try {
            InputStream inputStream = new ClassPathResource("Date.json").getInputStream();
            containerModel = objectMapper.readValue(inputStream,
                    EmailContainerModel.class);
            /*for (EmailOutputModel emailOutputModel : containerModel.getOrders()){
                System.out.println(emailOutputModel.getAbsender());
                System.out.println(emailOutputModel.getEmpfaenger());
                System.out.println(emailOutputModel.getBetriff());
                System.out.println(emailOutputModel.getEmailBodyModel().getAbholort());
                System.out.println(emailOutputModel.getEmailBodyModel().getAbholzeit());
                System.out.println(emailOutputModel.getEmailBodyModel().getZustellort());
                System.out.println(emailOutputModel.getEmailBodyModel().getZustellzeit());
            }*/
        }catch (IOException e){
            System.out.println(e);
        }
       return containerModel;
    }

}
