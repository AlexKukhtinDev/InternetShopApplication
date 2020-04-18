package com.akukhtin.internetshop.controller;

import com.akukhtin.internetshop.model.Draft;
import com.akukhtin.internetshop.repository.DraftRepository;
import com.akukhtin.internetshop.util.PDFGenerator;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class DraftController {

    @Autowired
    private DraftRepository draftRepository;
    @GetMapping (value = "/drafts", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> draftsReport() throws DocumentException {
        List<Draft> customers = draftRepository.findAll();

        ByteArrayInputStream bis = PDFGenerator.draftPDFReport(customers);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=draft.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
