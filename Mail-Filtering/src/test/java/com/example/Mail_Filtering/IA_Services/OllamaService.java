package com.example.Mail_Filtering.IA_Services;

import com.example.Mail_Filtering.Models.EmailContainerModel;
import com.example.Mail_Filtering.Models.EmailInputModel;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OllamaService {

    @Mock
    private EmailContainerModel emailContainerModel;
    @Mock
    private EmailInputModel emailInputModel;

    @InjectMocks
    private OllamaServiceImpl ollamaService;

    @Test
    public void mailSplitAiResponse(){
        EmailContainerModel emailContainerModel = null;
        EmailInputModel emailInputModel1 = EmailInputModel.builder().absender("logistik" +
                "@transportservice.de").empfaenger("dispatcher@meinunternehmen.de").betriff(
                        "Fahraufträge für den 26.11.2025 – mehrere Touren").body(" " +
                "\r\nBeschreibung Ware Empfänger (Ort) Absender (Ort) Auftragsnummer \r\n1.200 | " +
                "\r\n01.12.2025 | \r\ngeplant \r\n \r\n \r\nElektronikpalette\r\nn \r\nMünchen " +
                "Hamburg TA-001 \r\n4.500 | \r\n02.12.2025 | \r\nbestätigt " +
                "\r\nMaschinenbautei\r\nle \r\nParis Berlin TA-002 \r\n \r\n800 | 03.12.2025 " +
                "\r\n| in Planung \r\nChemikalien \r\n(Gefahrgut) \r\nZürich Frankfurt \r\n " +
                "\r\nTA-003 \r\n \r\n2.100 | \r\n04.12.2025 | \r\nbestätigt \r\n \r\n \r\nMöbel " +
                "(Sperrgut) Wien Köln TA-004 \r\n450 | 05.12.2025 \r\n| geplant \r\nTextilien (30" +
                " \r\nKartons) \r\nRom Stuttgart TA-005 \r\n \r\n").build();
        when(ollamaService.mailSplitAiResponse(Mockito.any(EmailInputModel.class))).thenReturn(emailContainerModel);

        EmailContainerModel emailContainerModel1 =
                ollamaService.mailSplitAiResponse(emailInputModel1);

        Assertions.assertThat(emailContainerModel1).isNotNull();

    }
}
