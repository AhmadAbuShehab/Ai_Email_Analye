package com.example.Mail_Filtering.RestController;

import com.example.Mail_Filtering.IA_Services.OllamaServiceImpl;
import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;

import com.example.Mail_Filtering.Models.PdfTextResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;



@RestController
@RequestMapping("/api")
public class AiController {

    @Autowired
    private OllamaServiceImpl ollamaService;

    @PostMapping("multi")
    public ResponseEntity<EmailContainerModel> mailSplit(@RequestBody HashMap<String,
                                                         String> request){
        EmailInputModel emailInputModel = new EmailInputModel(request.get("absender"), request.get(
                "empfaenger"),request.get("betriff"), request.get("body"));
        EmailContainerModel emailsplitresult =
                ollamaService.mailSplitAiResponse(emailInputModel);

       // if ( emailsplitresult.getStatus().equals("success")){
            return ResponseEntity.ok(emailsplitresult);
        //}else{
          //  return ResponseEntity.badRequest().body(emailsplitresult);
        //}
    }


    @GetMapping("/read")
    public ResponseEntity<EmailContainerModel> readMultimails(){

        //if (ollamaService.readJsonFromFile().getStatus().equals("success")){
            ollamaService.readJsonFromFile();
            return ResponseEntity.ok(ollamaService.readJsonFromFile());
        //}else {
          //  return ResponseEntity.badRequest().body(ollamaService.readJsonFromFile());
        //}
    }


    @PostMapping("/pdf")
    public ResponseEntity<PdfTextResponseModel> extractPdfFile(@RequestParam("file") MultipartFile file) {
        // Der Controller ruft nur noch den Service auf
        PdfTextResponseModel response = ollamaService.readPdfFile(file);

        // Basierend auf dem Status im Response-Objekt den richtigen HTTP-Status setzen
        if ("success".equals(response.getStatus())) {
            return ResponseEntity.ok(response); // HTTP 200 OK
        } else if (response.getStatus().contains("Keine Datei")) {
            return ResponseEntity.badRequest().body(response); // HTTP 400 Bad Request
        } else {
            return ResponseEntity.status(500).body(response); // HTTP 500 Internal Server Error
        }
    }


    @PostMapping("/pdfres")
    public ResponseEntity<PdfTextResponseModel> readPdfExtraction(@RequestParam("file") MultipartFile file) {
        // Der Controller ruft nur noch den Service auf
        PdfTextResponseModel response = ollamaService.pdfFileSpliter(ollamaService.readPdfFile(file));

        // Basierend auf dem Status im Response-Objekt den richtigen HTTP-Status setzen
        if ("success".equals(response.getStatus())) {
            return ResponseEntity.ok(response); // HTTP 200 OK
        } else if (response.getStatus().contains("Keine Datei")) {
            return ResponseEntity.badRequest().body(response); // HTTP 400 Bad Request
        } else {
            return ResponseEntity.status(500).body(response); // HTTP 500 Internal Server Error
        }
    }
}
