package com.example.Mail_Filtering.IA_Services;

import com.example.Mail_Filtering.IA_Services.Http_AI.AiRequest;
import com.example.Mail_Filtering.IA_Services.Http_AI.AiResponse;
import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;
import com.example.Mail_Filtering.Models.EmailOutputModel;
import com.example.Mail_Filtering.Models.PdfTextResponseModel;
//import com.example.Mail_Filtering.Repoditory.AiEmailAnalyse; erst bei der DB Verbindung
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class OllamaServiceImpl implements OllamaService {
    @Autowired
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    @Autowired
    private EmailContainerModel emailContainerModel;


    public OllamaServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public EmailContainerModel mailSplitAiResponse(EmailInputModel emailInputModel) {
        String prompt = /*"AUFGABE:\n" +
                "Du erhältst ein JSON-Objekt „email“. Analysiere ausschließlich das Feld \"body\".\n" +
                "Im Body stehen Transportaufträge in natürlicher Sprache.\n" +
                "\n" +
                "Für jeden Auftrag erstelle ein JSON-Objekt nach folgendem Schema, und für den " +
                "absender,empfenger und betriff über nimmst du die Werte aus dem " +
                "orginallen text:" +
                "{"+
                "absender": "%s",
                "empfaenger": "%s",
                "betriff": "%s",
                "body: {" +
                "order: " +
                "ware: " +
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
                "{{"+emailInputModel.getBody() +"}}";*/
                 """
                 AUFGABE:\\n" +
                 "Du erhältst ein JSON-Objekt „email“. Analysiere ausschließlich das Feld \\"body\\".\\n" +
                 "Im Body stehen Transportaufträge in natürlicher Sprache.\\n" +
                 "\\n" +
                 "Für jeden Auftrag erstelle ein JSON-Objekt nach folgendem Schema, und für den " +
                 "absender,empfenger und betriff über nimmst du die Werte aus email-absender, Email-empfaenger und Email-betriff dem " +
                 "orginallen text:" +
                 "{"+
                 "absender": "%s",
                 "empfaenger": "%s",
                 "betriff": "%s",
                 "body: {" +
                 "order: " +
                 "ware: " +
                 "abholort: " +
                 "abholzeit: " +
                 "zustellort: "+
                 "zustellzeit:"+
                 "}"+

                Gib das Ergebnis NUR als folgendes JSON zurück:
                
                {
                  "orders": [ ... ]
                }
                
                Falls Informationen im Text fehlen, lasse die Felder leer, aber behalte die Struktur bei.
                
                KEIN zusätzlicher Text.
                KEINE Kommentare.
                KEINE Erklärungen.
                DAS ERGEBNIS MUSS IN EINER GÜLTIGE LESBARE JSON FORM SEIN.
                
                Hier ist die Email:
                
                %s
                """.formatted(
                emailInputModel.getAbsender(),
                emailInputModel.getEmpfaenger(),
                emailInputModel.getBetriff(),
                emailInputModel.getBody());
        String model = "llama3";
        AiRequest aiRequest = new AiRequest(model, prompt, false);
        AiResponse aiResponse = restTemplate.postForObject(OLLAMA_URL, aiRequest,AiResponse.class);
        assert aiResponse != null;//hier muss noch der Exception empfangen
        System.out.println(aiResponse.getResponse());

        String filePath = "C:/Users/ahmad/Desktop/Spring_boot_projects/Mail-Filtering (1)" +
                "/Mail-Filtering/src/main/resources/Date.json";
        try {
            Files.writeString(Path.of(filePath), aiResponse.getResponse());
            emailInputModel.setStatus("success");
            System.out.println("status : "+ emailInputModel.getStatus());
        } catch (Exception e) {
            emailInputModel.setStatus("Failed");
            throw new RuntimeException("Fehler beim Schreiben der JSON-Datei: " + filePath, e);
        }
        return readJsonFromFile();
    }

    @Override
    public PdfTextResponseModel pdfFileSpliter(PdfTextResponseModel pdfTextResponse) {
        String prompt = "You are an expert logistics data extractor. \n" +
                "Your task is to read the text content extracted from customer PDF files and convert it into clean, structured JSON.\n" +
                "\n" +
                "=== GOALS ===\n" +
                "1. Extract ALL logistics orders contained in the provided text.\n" +
                "2. Each order must become a separate JSON object.\n" +
                "3. Return an array of JSON objects.\n" +
                "4. The JSON must contain ONLY fields that exist in the text. Do not guess.\n" +
                "\n" +
                "=== REQUIRED JSON FIELDS (if present in text) ===\n" +
                "- \"customerName\"\n" +
                "- \"orderNumber\"\n" +
                "- \"pickupAddress\"\n" +
                "- \"deliveryAddress\"\n" +
                "- \"pickupDate\"\n" +
                "- \"deliveryDate\"\n" +
                "- \"cargoType\"\n" +
                "- \"cargoWeight\"\n" +
                "- \"cargoVolume\"\n" +
                "- \"specialInstructions\"\n" +
                "\n" +
                "If some information is missing, set the value to null.\n" +
                "NEVER invent or hallucinate content.\n" +
                "\n" +
                "=== RULES ===\n" +
                "- The input text may be written as continuous text, bullet points, or inside a table.\n" +
                "- Extract all orders, even if they appear in multiple sections.\n" +
                "- Maintain accuracy: if unsure about a value, use null.\n" +
                "- Never change or reinterpret the meaning of the text.\n" +
                "- Output MUST be valid JSON.\n" +
                "\n" +
                "=== OUTPUT FORMAT (STRICT) ===\n" +
                "Return ONLY the final JSON array.\n" +
                "No explanations, no comments, no natural language.\n" +
                "\n" +
                "Example output format:\n" +
                "[\n" +
                "  {\n" +
                "    \"customerName\": \"...\",\n" +
                "    \"orderNumber\": \"...\",\n" +
                "    \"pickupAddress\": \"...\",\n" +
                "    \"deliveryAddress\": \"...\",\n" +
                "    \"pickupDate\": \"...\",\n" +
                "    \"deliveryDate\": \"...\",\n" +
                "    \"cargoType\": \"...\",\n" +
                "    \"cargoWeight\": \"...\",\n" +
                "    \"cargoVolume\": \"...\",\n" +
                "    \"specialInstructions\": \"...\"\n" +
                "  }\n" +
                "]\n" +
                "\n" +
                "\n" +
                "KEIN zusätzlicher Text.\n" +
                "KEINE Kommentare.\n" +
                "KEINE Erklärungen."+
                "Hier ist die Email:\n" +
                "\n" +
                "=== INPUT TEXT START ===\n" +
                "{{"+pdfTextResponse.getContent()+"}}\n" +
                "=== INPUT TEXT END ===";
        String model = "llama3";
        AiRequest aiRequest = new AiRequest(model, prompt, false);
        AiResponse aiResponse = restTemplate.postForObject(OLLAMA_URL, aiRequest,AiResponse.class);
        assert aiResponse != null;//hier muss noch der Exception empfangen
        System.out.println(aiResponse.getResponse());

        String filePath = "C:/Users/ahmad/Desktop/Spring_boot_projects/Mail-Filtering (1)" +
                "/Mail-Filtering/src/main/resources/Date.json";
        try {
            pdfTextResponse.setStatus("success");
            return pdfTextResponse;
        } catch (Exception e) {
            pdfTextResponse.setStatus("Failed");
            throw new RuntimeException("Fehler beim Schreiben der JSON-Datei: " + filePath, e);
        }
    }

    @Override
    public EmailContainerModel readJsonFromFile()  {
        EmailContainerModel containerModel = null;
        try {
            InputStream inputStream = new ClassPathResource("Date.json").getInputStream();
            containerModel = objectMapper.readValue(inputStream,
                    EmailContainerModel.class);
            //containerModel.setStatus("success");
            for (EmailOutputModel emailOutputModel : containerModel.getOrders()){
                emailOutputModel.getAbsender();
                emailOutputModel.getEmpfaenger();
                emailOutputModel.getBetriff();
                emailOutputModel.getEmailBodyModel();

            }
        }catch (IOException e){
            //containerModel.setStatus("Faild");
            System.out.println(e);
        }
       return containerModel;
    }

    @Override
    public PdfTextResponseModel readPdfFile(MultipartFile file) {
        if (file.isEmpty()) {
            // Bei Fehlern kann man immer noch einen String zurückgeben oder eine Fehler-JSON
            return null;
        }

        try (/* 🔑 KORREKT: InputStream in RandomAccessReadBuffer wrappen */
                RandomAccessReadBuffer randomAccessRead = new RandomAccessReadBuffer(file.getInputStream());
                PDDocument document = Loader.loadPDF(randomAccessRead)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);

            // Erstellen Sie das Antwort-Objekt
            return new PdfTextResponseModel(
                    file.getOriginalFilename(),
                    extractedText.length(),
                    extractedText,
                    "success"
            );
        } catch (IOException e) {
            System.out.println("error");
            return new PdfTextResponseModel("",0, null, "error");
        }

    }

}
