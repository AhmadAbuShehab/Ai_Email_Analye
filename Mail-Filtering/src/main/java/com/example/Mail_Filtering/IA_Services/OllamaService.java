package com.example.Mail_Filtering.IA_Services;

import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;
import com.example.Mail_Filtering.Models.PdfTextResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface OllamaService {

    EmailContainerModel mailSplitAiResponse(EmailInputModel emailInputModel);

    PdfTextResponseModel pdfFileSpliter(PdfTextResponseModel pdfTextResponse);

    EmailContainerModel readJsonFromFile();

    PdfTextResponseModel readPdfFile(MultipartFile file);
}
