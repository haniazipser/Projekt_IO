package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FileService {
    private final KeycloakClientService keycloakClientService;
    public String createPDFList(List<ExerciseWithPointsDto> exercises1,List<ExerciseWithPointsDto> exercises2, LessonDescriptionDto lesson) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        LocalDate date = LocalDate.from(lesson.getClassDate().atZone(ZoneId.systemDefault()));
        String name = "List_" +lesson.getCourseName().replace(' ','_')+"_"+date.toString()+".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(name));

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);

        Paragraph title = new Paragraph("List for " + lesson.getCourseName() + " "+ date.toString() + " - by number of points", titleFont);
        title.setSpacingAfter(20);
        document.add(title);
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
        int i = 1;
        for (ExerciseWithPointsDto e : exercises2) {
            StringBuilder line = new StringBuilder();
            String user = keycloakClientService.getUserByEmail(e.getApprovedStudent()).block();

            line.append(i).append(". ").append(user).append(" —> ");

            line.append("Ex.").append(e.getExerciseNumber());

            if (e.getSubpoint() != null && !e.getSubpoint().isEmpty()) {
                line.append(e.getSubpoint());
            }

            Paragraph paragraph = new Paragraph(line.toString(), font);
            paragraph.setSpacingAfter(10);

            document.add(paragraph);
            i++;
        }
        document.newPage();
        title = new Paragraph("List for " + lesson.getCourseName() + " "+ date.toString() + " - by exercises", titleFont);
        title.setSpacingAfter(20);
        document.add(title);

        for (ExerciseWithPointsDto e : exercises1) {
            StringBuilder line = new StringBuilder();

            line.append("Ex.").append(e.getExerciseNumber());

            if (e.getSubpoint() != null && !e.getSubpoint().isEmpty()) {
                line.append(e.getSubpoint());
            }

            String user = keycloakClientService.getUserByEmail(e.getApprovedStudent()).block();

            line.append(" —> ").append(user);

            Paragraph paragraph = new Paragraph(line.toString(), font);
            paragraph.setSpacingAfter(10);

            document.add(paragraph);
        }

        document.close();
        return name;
    }
}
