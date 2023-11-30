package ru.clevertec.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import ru.clevertec.dto.PlayerDto;
import ru.clevertec.service.PlayerService;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfPrinter implements Printer {
    private static final String OUTPUT_FILE = "players.pdf";
    private static final String TEMPLATE_FILE = "Clevertec_Template.pdf";

    private final PlayerService playerService;

    public PdfPrinter(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void print() {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_FILE));

            document.open();

            addTemplateToDocument(writer);
            addPlayersToDocument(document);

            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTemplateToDocument(PdfWriter writer) throws IOException, DocumentException {
        PdfContentByte underContent = writer.getDirectContentUnder();

        PdfReader reader = new PdfReader(TEMPLATE_FILE);
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        underContent.addTemplate(page, 0, 0);
    }

    private void addPlayersToDocument(Document document) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont("src/main/resources/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font header_font = new Font(baseFont, 30, Font.BOLD);
        Font font = new Font(baseFont, 20);

        Paragraph paragraph = new Paragraph("\n".repeat(5));
        document.add(paragraph);
        Paragraph playerInfo = new Paragraph("Clevertec players:", header_font);
        playerInfo.setSpacingAfter(80);
        playerInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(playerInfo);

        for (PlayerDto player : playerService.getAll()) {
            Paragraph playerDetails = new Paragraph(
                    player.number() +
                            " - " + player.name() +
                            " " + player.surname() +
                            "(" + player.dateBirth() + ")"

                    , font);
            playerDetails.setAlignment(Element.ALIGN_CENTER);
            document.add(playerDetails);
        }
    }
}
