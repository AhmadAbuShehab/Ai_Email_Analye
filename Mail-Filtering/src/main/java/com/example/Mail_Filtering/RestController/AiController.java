package com.example.Mail_Filtering.RestController;

import com.example.Mail_Filtering.IA_Services.OllamaService;
import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;
import com.example.Mail_Filtering.Models.EmailOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AiController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("multi")
    public void multi(@RequestBody HashMap<String, String> request){
        EmailInputModel emailInputModelModel = new EmailInputModel(request.get("absender"), request.get(
                "empfaenger"),request.get("betriff"), request.get("body"));
        ollamaService.mailSplitAiResponse(emailInputModelModel);
    }

    @GetMapping("/read")
    public EmailContainerModel read(){
        return ollamaService.readJsonFromFile();
    }

}
