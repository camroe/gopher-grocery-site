package com.gophergroceries.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gophergroceries.model.dao.JsonUtils;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.OrderService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class PDFController {
	private static final Logger logger = LoggerFactory.getLogger(PDFController.class);
	private static final String RESULT = "/User/camroe/tmp/hello.pdf";

	public static final Font GGBOLD;
	public static final Font GGNORMAL;
	public static final Font GGBOLD_UNDERLINED;

	public static final Font BOLD_UNDERLINED = new Font(FontFamily.TIMES_ROMAN,
			12, Font.BOLD | Font.UNDERLINE);
	public static final Font NORMAL = new Font(FontFamily.TIMES_ROMAN, 12);

	static {
		BaseFont gothamBold = null;
		BaseFont gothamMedium = null;
		try {
			File file = new File("test.txt");
			logger.info(file.getAbsolutePath());
			logger.info(file.getCanonicalPath());

			logger.info(file.getAbsolutePath());
			logger.info(file.getCanonicalPath());
			// URL url = ClassLoader.class.getResource("FONTS/GothamMedium.ttf");
			// if (null == url) {
			// logger.error("URL is NULL");
			// System.exit(2);
			// }
			// else {
			gothamBold = BaseFont.createFont(
					"/com/gophergroceries/controllers/FONTS/GothamBold.otf",
					BaseFont.WINANSI, BaseFont.EMBEDDED);
			gothamMedium = BaseFont.createFont(
					"/com/gophergroceries/controllers/FONTS/GothamMedium.ttf",
					BaseFont.WINANSI, BaseFont.EMBEDDED);
			// }
		} catch (DocumentException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		GGBOLD = new Font(gothamBold, 12);
		GGNORMAL = new Font(gothamMedium, 12);
		GGBOLD_UNDERLINED = new Font(gothamBold, 12, Font.UNDERLINE);
	}

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/v1/pdf", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF(Model model) {
		OrderSummaryResult osr = orderService.getOrderSummary();
		model.addAttribute("orderSummaryResult", osr);
		String osJson = JsonUtils.JsonStringFromObject(osr);
		model.addAttribute("osJson", osJson);
		logger.info("PDF - OrderJSON: " + osJson);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		byte[] contents = null;
		try {
			contents = createPDF
					(osr).toByteArray();
		} catch (DocumentException | IOException e) {
			logger.error("Exception thrown by createPDF in PDFController");
			e.printStackTrace();
		}

		headers.setContentDispositionFormData(RESULT, RESULT);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		return response;
	}

	private ByteArrayOutputStream createPDF(OrderSummaryResult osr)
			throws DocumentException, IOException {
		Document document = new Document(PageSize.LETTER);
		// Or Landscape Document document = new Document(PageSize.LETTER.rotate());
		document.setMargins(36, 72, 108, 180);
		document.setMarginMirroring(true); // for books - even pages want the right
																			 // margin larger
		// Better to write to memory first, then a file. If you write to memory
		// first you can transfer the byte stream to something other than a file.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		writer.setInitialLeading(16); // Set line spacing
		document.open();
		document.add(new Paragraph("Hello World!"));
		// Adding Chunnks Example
		document.add(new Chunk(osr.getErrorMsg()));
		document.add(new Chunk(" "));
		Font font = new Font(
				FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id = new Chunk(osr.getOrderSummary().getOrder().getOrderEntity().getFirstName(), font);
		id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id.setTextRise(6);
		document.add(id);
		document.add(Chunk.NEWLINE);

		// Use Phrase (series of chunks) to display stuff.
		Phrase deliveryInfo = new Phrase();
		deliveryInfo.add(new Chunk("Order Number: ", GGBOLD_UNDERLINED));
		deliveryInfo.add(new Chunk(" ", GGNORMAL));
		deliveryInfo.add(new Chunk(osr.getOrderSummary().getOrder().getOrderEntity().getId().toString(), GGNORMAL));
		document.add(deliveryInfo);
		document.close();
		FileOutputStream fos = new FileOutputStream(RESULT);
		fos.write(baos.toByteArray());
		fos.close();
		logger.trace("In create PDF");
		return baos;
	}
}
