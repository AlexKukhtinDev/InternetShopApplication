package com.akukhtin.internetshop.util;

import com.akukhtin.internetshop.model.Draft;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PDFGenerator {
    public static ByteArrayInputStream draftPDFReport(List<Draft> drafts) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Draft Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            // Add PDF Table Header ->
            Stream.of("NameProduct", "EAN", "PriceProduct", " TotalAmount")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });


        for (Draft draft : drafts) {
//            PdfPCell idCell = new PdfPCell(new Phrase(draft.getId()));
//            idCell.setPaddingLeft(4);
//            idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(idCell);

            PdfPCell NameProductCell = new PdfPCell(new Phrase(draft.getNameProduct()));
            NameProductCell.setPaddingLeft(4);
            NameProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            NameProductCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(NameProductCell);

            PdfPCell eanCell = new PdfPCell(new Phrase(String.valueOf(draft.getEan())));
            eanCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            eanCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            eanCell.setPaddingRight(4);
            table.addCell(eanCell);

            PdfPCell priceProduct =
                    new PdfPCell(new Phrase(String.valueOf(draft.getPriceProduct())));
            priceProduct.setVerticalAlignment(Element.ALIGN_MIDDLE);
            priceProduct.setHorizontalAlignment(Element.ALIGN_RIGHT);
            priceProduct.setPaddingRight(4);
            table.addCell(priceProduct);

            PdfPCell totalAmount = new PdfPCell(new Phrase(String.valueOf(draft.getTotalAmount())));
            totalAmount.setVerticalAlignment(Element.ALIGN_MIDDLE);
            totalAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalAmount.setPaddingRight(4);
            table.addCell(totalAmount);
        }
        document.add(table);

        document.close();

    return new ByteArrayInputStream(out.toByteArray());
    }
}
