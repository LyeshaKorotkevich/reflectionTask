package ru.clevertec.view;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.service.PlayerService;
import util.PlayerTestData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PdfPrinterTest {
    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PdfPrinter pdfPrinter;

//    @Test
//    public void printShouldInvokeService() {
//        // given
//        List<PlayerDto> players = Arrays.asList(
//                PlayerTestData.builder().build().buildPlayerDto(),
//                PlayerTestData.builder().withName("NoOne").build().buildPlayerDto()
//        );
//
//        when(playerService.getAll())
//                .thenReturn(players);
//
//        // when
//        pdfPrinter.print();
//
//        // then
//        verify(playerService)
//                .getAll();
//    }

    @Test
    void testPrintGeneratesPdfWithPlayers() throws IOException {
        // given
        List<PlayerDto> players = Arrays.asList(
                PlayerTestData.builder().build().buildPlayerDto(),
                PlayerTestData.builder().withName("NoOne").build().buildPlayerDto()
        );

        when(playerService.getAll())
                .thenReturn(players);

        // when
        pdfPrinter.print();

        // then
        PdfReader reader = new PdfReader("players.pdf");
        StringBuilder textContent = new StringBuilder();

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            String pageText = PdfTextExtractor.getTextFromPage(reader, i);
            textContent.append(pageText);
        }

        assertTrue(textContent.toString().contains("Lyesha"));
        assertTrue(textContent.toString().contains("NoOne"));

    }
}