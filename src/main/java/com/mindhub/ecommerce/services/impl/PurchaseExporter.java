package com.mindhub.ecommerce.services.impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;
import com.mindhub.ecommerce.models.AppUser;
import com.mindhub.ecommerce.models.PaymentOption;
import com.mindhub.ecommerce.models.Purchase;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PurchaseExporter {
    public void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefix) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String filename = prefix + timeStamp + extension;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; fileName=" + filename;
        response.setHeader(headerKey, headerValue);
    }

    public void exportToPDF(Purchase purchase, AppUser appUser, HttpServletResponse response) throws DocumentException, IOException{
        setResponseHeader(response, "application/pdf", ".pdf", "Transactions");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Image logo = com.lowagie.text.Image.getInstance("heroes-vault-logo-alt.png");
        logo.scalePercent(6);
        logo.setAlignment(Image.ALIGN_CENTER);

        document.add(logo);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(22);
        font.setColor(Color.BLACK);

        Paragraph title = new Paragraph(("User: " + appUser.getUsername()), font);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);

        purchase.getComics().forEach(comic -> {
            Paragraph item = new Paragraph("Comic: " + comic.getTitle() + " - $" + comic.getPrice());
            item.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(item);
        });

        purchase.getMerch().forEach(merch -> {
            Paragraph item = new Paragraph("Merch: " + merch.getName() + " - $" + merch.getPrice());
            item.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(item);
        });

        Paragraph amount = new Paragraph("Total: $" + purchase.getAmount());
        amount.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(amount);

        Paragraph paymentOption = new Paragraph("Payment method: " + purchase.getPaymentOption());
        paymentOption.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paymentOption);

        if (purchase.getPaymentOption() == PaymentOption.CARD){
            Paragraph cardNumber = new Paragraph("Card number: " + purchase.getCardNumber());
            cardNumber.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(cardNumber);
        }

        document.close();
    }
}
