package com.customer.management.tool.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.customer.management.tool.dao.impl.CMTReportDaoImpl;
import com.customer.management.tool.pojo.CustomerJobDetail;
import com.customer.management.tool.pojo.ReportGenerator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class CMTReportGeneratorImpl {

	@Autowired
	private CMTReportDaoImpl reportDaoImpl;

	public List<CustomerJobDetail> startToEndDateRecords(
			ReportGenerator reportGenerator) {
		return reportDaoImpl.startToEndDate(reportGenerator);
	}

	public List<CustomerJobDetail> MonthlyReports(
			ReportGenerator reportGenerator) {

		return reportDaoImpl.monthlyReportGenerator(reportGenerator);
	}

	public boolean generateExcelReport(JTable table) {

		boolean success = false;
		try {
			Workbook wb = new XSSFWorkbook(); // Excell workbook
			Sheet sheet = wb.createSheet(); // WorkSheet
			Row row = sheet.createRow(2); // Row created at line 3
			TableModel model = table.getModel(); // Table model

			Row headerRow = sheet.createRow(0); // Create row at line 0
			// For each column
			for (int headings = 0; headings < model.getColumnCount(); headings++) {
				// Write column name
				headerRow.createCell(headings).setCellValue(
						model.getColumnName(headings));
			}

			// For each table row
			for (int rows = 0; rows < model.getRowCount(); rows++) {
				// For each table column
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					// Write value
					row.createCell(cols).setCellValue(
							model.getValueAt(rows, cols).toString());
				}
				// Set the row to the next one in the sequence
				row = sheet.createRow((rows + 3));
			}
			fileSaved(".xls", wb, null);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	public boolean generatePdfReport(JTable table) {

		boolean success = false;
		try {
			Document doc = new Document();
			fileSaved(".pdf", null, doc);
			//doc.open();
			Paragraph paragraph = new Paragraph(
			        " Customer Report",new Font(Font.FontFamily.HELVETICA, 25));
			paragraph.setAlignment(1);
			paragraph.setSpacingAfter(23);
			doc.add(paragraph);
			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
			// adding table headers
			PdfPCell cell = new PdfPCell();
			cell.setColspan(table.getColumnCount());
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);			
			
			pdfTable.addCell(cell);
			for (int i = 0; i < table.getColumnCount(); i++) {
				pdfTable.addCell(table.getColumnName(i));
			}
			// extracting data from the JTable and inserting it to PdfPTable
			for (int rows = 0; rows < table.getRowCount(); rows++) {
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					pdfTable.addCell(table.getModel().getValueAt(rows, cols)
							.toString());

				}
			}
			doc.addTitle("Report");
			doc.add(pdfTable);
			doc.close();
			System.out.println("done");
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			success = false;
		}
		return success;
	}

	private void fileSaved(String fileExtension, Workbook wb, Document doc)
			throws IOException, DocumentException {
		   String IMAGE = "resources/images/cmtreport_pdftbackground.jpg";
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home/me/Documents"));
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try (FileWriter fw = new FileWriter(chooser.getSelectedFile()
					+ fileExtension)) {
				if (doc == null && fileExtension.equals(".xls")) {
					// Save the file
					wb.write(new FileOutputStream(chooser.getSelectedFile()
							+ fileExtension));
				} else {
				
					com.itextpdf.text.pdf.PdfWriter writer=	PdfWriter.getInstance(doc,
							new FileOutputStream(chooser.getSelectedFile()
									+ fileExtension));
					doc.open();
					PdfContentByte canvas = writer.getDirectContentUnder();
					 Image image = Image.getInstance(IMAGE);
					 image.scaleAbsolute(PageSize.A4.getWidth(),PageSize.A4.getHeight());
				        image.setAbsolutePosition(0, 0);
				        canvas.addImage(image);
				}
			}
		}
	}
}
