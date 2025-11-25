package com.example.Mail_Filtering.IA_Services;

import com.example.Mail_Filtering.IA_Services.Http_AI.AiRequest;
import com.example.Mail_Filtering.IA_Services.Http_AI.AiResponse;
import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;
import com.example.Mail_Filtering.Models.EmailOutputModel;

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


    public void mailSplitAiResponse(EmailInputModel emailInputModel) {
        AiRequest aiRequest = new AiRequest();
        aiRequest.setModel("gpt-4o");
        aiRequest.setPrompt("AUFGABE:\n" +
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
                                "Abholort: " +
                                "Abholzeit: " +
                                "Zustellort: "+
                                "Zustellzeit:"+
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
                                "{{"+emailInputModel.getBody() +"}}");
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
