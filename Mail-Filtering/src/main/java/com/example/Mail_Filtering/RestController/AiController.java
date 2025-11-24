package com.example.Mail_Filtering.RestController;

import com.example.Mail_Filtering.IA_Services.OllamaService;
import com.example.Mail_Filtering.Models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AiController {

    @Autowired
    private OllamaService ollamaService;

    //@Autowired
    //private EmailModel emailModel;


    @PostMapping("multi")
    public void multi(@RequestBody HashMap<String, String> request){
        EmailModel emailModel = new EmailModel(request.get("absender"), request.get(("empfaenger")), request.get("betreff"), request.get("body"));
        ollamaService.mailSplitAiResponse(emailModel);
    }

    @GetMapping("/read")
    public String read(){
        return ollamaService.readJsonFromFile();
    }

}
