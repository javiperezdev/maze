package classes;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;


public class ReportGenerator {
	
	public static void writePdf(Session session, Maze maze) {
		Document document = new Document();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "report_" + timestamp + ".pdf";
        String filePath = "./assets/" + fileName;
        
		try (FileOutputStream f = new FileOutputStream(filePath)) {
			PdfWriter.getInstance(document, f);
			headerBuilder(document, session.getUser());
			document.open();	
			mazeName(document, maze.getFilename());
			representMaze(document, maze);
			representPath(document, maze);
			document.close();
			
			File generatedFile = new File(filePath);
            System.out.println("Report generated in: " + generatedFile.getAbsolutePath());
		}
		
		catch (Exception e) {
			System.out.println("Failed to generate PDF!");
			LogGenerator.generateLog("Failed to generate PDF", "details: " + e);
		}
	}
	
	public static void headerBuilder(Document document, User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("MazeSolver | Version: ").append(Config.VERSION).append(" | user: ").append(user.getUsername());
		String headerText = sb.toString();
		HeaderFooter header = new HeaderFooter(new Phrase(headerText), false);
		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorder(Rectangle.BOTTOM);
		document.setHeader(header);
	}
	
	public static void mazeName(Document document, String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("Solved maze: ").append(fileName);
		Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);
        Paragraph p = new Paragraph(sb.toString(), normalFont);
		p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
	
	}
	
	public static void representMaze(Document document, Maze maze) {
		char[][] map = maze.getMap();
		Stack<Coordinate> path = new Stack<Coordinate>();
		path.addAll(maze.getPath());
		
		int rows = map.length;
		int cols = map[0].length;
		char[][] mapCopy = new char[rows][cols];
	    
		for (int i = 0; i < path.size() - 1; i++) {
			int row = path.get(i).getI();
			int col = path.get(i).getJ();
			mapCopy[row][col] = path.get(i+1).getDirection();
		}
		

		
		Paragraph p = new Paragraph(" ");
		PdfPTable table = new PdfPTable(cols);	
		try {
			Image wall = Image.getInstance("assets/wall.png");
			Image start = Image.getInstance("assets/start.png");
			Image end = Image.getInstance("assets/end.png");
			Image downArrow = Image.getInstance("assets/down-arrow.png");
			Image rightArrow = Image.getInstance("assets/right-arrow.png");
			Image leftArrow = Image.getInstance("assets/left-arrow.png");
			Image upArrow = Image.getInstance("assets/up-arrow.png");
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					PdfPCell cell = new PdfPCell();
					char currentValue = mapCopy[i][j];
		
					if (i == maze.getStartI() && j == maze.getStartJ()) {
						cell.addElement(start);
					}
					else if (i == maze.getEndI() && j == maze.getEndJ()) {
						cell.addElement(end);
					}
					else if (map[i][j] == '#') {
						cell.addElement(wall);
					}
					else {
						switch (currentValue) {
				            case '>': cell.addElement(rightArrow); break;
				            case '<': cell.addElement(leftArrow); break;
				            case '^': cell.addElement(upArrow); break;
				            case 'v': cell.addElement(downArrow); break;
						}
					}
					table.addCell(cell);
				}
			}
			document.add(p);
			document.add(table);
		}
		catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	public static void representPath(Document document, Maze maze) {
		StringBuilder sb = new StringBuilder();
		Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

		Coordinate[] stepsArray = maze.getPath().toArray(new Coordinate[0]);
		sb.append("Steps: ").append(stepsArray.length).append("\n");
		
		Paragraph title = new Paragraph(sb.toString());
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(10f);
		title.setSpacingAfter(15f);
		PdfPTable table = new PdfPTable(4);

		for (int k = 0; k < stepsArray.length - 1; k++) {
			StringBuilder sb2 = new StringBuilder();
			Coordinate current = stepsArray[k];
			Coordinate next = stepsArray[k+1];
			String step = (sb2.append(k+1).append(") [").append(current.getI()).append(", ").append(current.getJ()).append("] ").append(next.getReadableDirection()).append("\n")).toString();
			Phrase p = new Phrase(step, normalFont);
			PdfPCell cell = new PdfPCell(p);	
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
		}
		table.completeRow();
		document.add(title);
		document.add(table);
	}
}
