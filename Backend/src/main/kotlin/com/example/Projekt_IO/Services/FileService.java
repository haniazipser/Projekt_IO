package com.example.Projekt_IO.Services;

import com.example.Projekt_IO.Model.Dtos.DeclarationDto;
import com.example.Projekt_IO.Model.Dtos.ExerciseDto;
import com.example.Projekt_IO.Model.Dtos.LessonDto;
import com.example.Projekt_IO.Model.Dtos.UserDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FileService {
    private final KeycloakClientService keycloakClientService;
    public void createPDFList(Set<ExerciseDto> exercises, LessonDto lesson) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("List.pdf"));

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
        Paragraph title = new Paragraph("List for " + lesson.getCourseName() + " "+ lesson.getClassDate().toString(), titleFont);
        title.setSpacingAfter(20);
        document.add(title);
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        for (ExerciseDto e : exercises) {
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
    }
}
