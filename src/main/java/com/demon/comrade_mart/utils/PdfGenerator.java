package com.demon.comrade_mart.utils;

import com.demon.comrade_mart.dto.ViewProductDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class PdfGenerator {

    public byte[] generatePdf(List<ViewProductDTO> products) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, PDRectangle.A4.getHeight() - 50);
            contentStream.showText("Order Details");
            contentStream.endText();

            // Define table parameters
            float margin = 50;
            float yStart = PDRectangle.A4.getHeight() - 70;
            float tableWidth = PDRectangle.A4.getWidth() - 2 * margin;
            float yPosition = yStart;
            float rowHeight = 20;
            float cellMargin = 2;

            // Define column headers
            String[] headers = {"Order ID", "Product Name", "Price", "Quantity", "Total Price", "Delivered", "Address", "Payment Mode"};
            float[] columnWidths = {50, 100, 50, 50, 70, 50, 150, 100};

            // Draw table headers
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            float nextX = margin;
            for (int i = 0; i < headers.length; i++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(headers[i]);
                contentStream.endText();
                nextX += columnWidths[i];
            }
            yPosition -= rowHeight;

            // Draw table rows
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            for (ViewProductDTO product : products) {
                nextX = margin;
                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(String.valueOf(product.getOrderid()));
                contentStream.endText();
                nextX += columnWidths[0];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(product.getProductName());
                contentStream.endText();
                nextX += columnWidths[1];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(String.valueOf(product.getProductPrice()));
                contentStream.endText();
                nextX += columnWidths[2];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(String.valueOf(product.getQuantity()));
                contentStream.endText();
                nextX += columnWidths[3];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(String.valueOf(product.getTotalPrice()));
                contentStream.endText();
                nextX += columnWidths[4];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                System.out.println(product.getProductName()+"= "+product.isDelivered());
                contentStream.showText(product.isDelivered() ? "Yes" : "No");
                contentStream.endText();
                nextX += columnWidths[5];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                contentStream.showText(product.getAddress());
                contentStream.endText();
                nextX += columnWidths[6];

                contentStream.beginText();
                contentStream.newLineAtOffset(nextX, yPosition);
                if (product.getModeOfPayment().equalsIgnoreCase("CashonDelivery")) {
                    //contentStream.newLineAtOffset(x, y);
                    contentStream.showText("Cash on Delivery (COD)");
                } else {
                    //contentStream.newLineAtOffset(x, y);
                    contentStream.showText("Other Payment Method");
                }
                contentStream.endText();
                nextX += columnWidths[7];

                yPosition -= rowHeight;
            }
            contentStream.close();
            System.out.println("PdfGenerator.generatePdf pdf generated");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }
}
