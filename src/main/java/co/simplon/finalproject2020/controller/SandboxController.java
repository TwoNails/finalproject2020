package co.simplon.finalproject2020.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.model.__test__Student;
import co.simplon.finalproject2020.repository.AttachedDocumentRepository;
import co.simplon.finalproject2020.repository.StudentRepository;

@RestController
@RequestMapping("/tests")
@CrossOrigin(origins = "http://localhost:4200")
public class SandboxController {
	
	@Autowired
	private AttachedDocumentRepository attachedDocumentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/pdf")		// reading a PDF, turning it into byte[], building a copy of the pdf from the byte[]. Then the same thing but with persistence.
	public void testStoringFile() throws IOException {
		
		File file = ResourceUtils.getFile("classpath:pdftest.pdf");	// allow us to get a file located in the project.
		File file2 = ResourceUtils.getFile("classpath:test.txt");
		File file3 = ResourceUtils.getFile("classpath:angryreact.png");
		
		// File is found
		System.out.println("File Found : " + file.exists());
		System.out.println("File2 Found : " + file2.exists());
		System.out.println("File3 Found : " + file3.exists());
		
		// We 'read' the .pdf file (we use FileInputStream to turn it into a byte array which is a type of object Java can actually manipulate)
		FileInputStream fis = new FileInputStream(file);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		// We build a short byte array which we'll use as a buffer.
		byte [] buf = new byte[1024];
		
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);								// the read function uses as a parameter the variable in which the data will be stored. 
				System.out.println("read " + readNum + " bytes, ");		// Kind of counterintuitive if you ask me wouldn't it make more sense to have this method  
			}															// return something instead of being void ?
		} catch (IOException e) {
			System.out.println("Problème dans la classe SandboxController");
		}
		
		byte[] bytesToStore = bos.toByteArray();
		System.out.println("Content of bos (bos.toString)" + bos);
		System.out.println("Content of bytesToStore" + bytesToStore);			// returns some cryptic value, likely a memory adress
		System.out.println("content of bytesToStore[1] " + bytesToStore[1]);	// seem to return actual content, more tests on the way
		
		// END OF THE CONVERSION TO BYTES
		
		// CONVERSION BACK FROM BYTES
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bytesToStore);
		
		
		File copy = new File("src/main/resources/copy.pdf");
		// System.out.println("where the f*** is that : " + copy.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(copy);
		fos.write(bytesToStore);
		fos.flush();
		fos.close();
		
		
			
		/* TESTS : SAVE PDF FROM LOCAL RESOURCES IN DATABASE */
		
		//AttachedDocument testDocument = new AttachedDocument(355, bytesToStore);
		//attachedDocumentRepository.saveAndFlush(testDocument);
		
		/* TESTS : READING DATABASE AND CREATING A COPY OF THE DOCUMENT FROM THE DATA FETCHED */
		
		Optional<AttachedDocument> copyFromDataBase = attachedDocumentRepository.findById(1);
		if(copyFromDataBase.isPresent()) {
			AttachedDocument documentToCopy = copyFromDataBase.get();
			File copy2 = new File("src/main/resources/copy2.pdf");		// we describe the route where we want to build the file
			FileOutputStream fos2 = new FileOutputStream(copy2);
			fos2.write(documentToCopy.getContent());
			fos2.flush();
			fos2.close();
			
			// NOTE : IF WE WANT TO DO THINGS PROPERLY, WE HAVE TO ADD A NAME AND A FILE EXTENSION TO THE MODELE AND BUILD THE PATH WITH IT.
		}
	}
	
	
	
	@GetMapping("/excel")	// reading a xls, turning it to objects, storing them in DB, then searching the db for specific data and turning it into another xls
	public void testExcelToObject() throws IOException, InvalidFormatException {
		
		/*
		 * Note : the Apache poi library for dealing with excels mostly deals in 4 kinds of Objects : Workbook, Sheets, Rows and Cells
		 * */
		
		final String PATH_OF_EXCEL_TO_READ = "src/main/resources/ExcelPourJava.xlsx";
		
		// Creating a Workbook from an Excel File (indifferently .xls or .xlsx)
		
		Workbook workbook = WorkbookFactory.create(new File(PATH_OF_EXCEL_TO_READ));
		
		Sheet studentSheet = workbook.getSheetAt(1);
		
		DataFormatter dataFormatter = new DataFormatter();
		
		System.out.println("We start iterating over row and columns");
		
		List<__test__Student> studentsFromExcel = new ArrayList<__test__Student>();
		
		
		__test__Student studentToSave;
		
		for(Row row: studentSheet) {
			
			studentToSave = new __test__Student();
			
			for(Cell cell: row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				System.out.print(cellValue +"\t\t");
				
				if(!cellValue.isEmpty() && !cellValue.isBlank()) {
					if(cell.getColumnIndex() == 0 && !cellValue.equals("NOM")) {
						studentToSave.setLastName(cellValue);
					} else if(cell.getColumnIndex() == 1 && !cellValue.equals("Prenom")) {
						studentToSave.setFirstName(cellValue);
					} else if(cell.getColumnIndex() == 2 && !cellValue.equals("Moyenne")) {
						cellValue = cellValue.replace(',', '.');
						studentToSave.setOverallAverage(Float.parseFloat(cellValue));
					}
				}	
			}
			
			System.out.println(studentToSave);
			//Optional<String> studentHasName = studentToSave.getFirstName();
			if(studentToSave.getFirstName() != null){
				studentsFromExcel.add(studentToSave);
			}
		}
		
		studentRepository.saveAll(studentsFromExcel);
		
		// PREVIOUS PART WORKED, NOW ON TO THE XLSX BUILDING PART
		
		List<__test__Student> studentsPassingTheYear = studentRepository.findAllByOverallaverageGreaterThanEqual(10.0f);
		System.out.println(studentsPassingTheYear);
		
		String[] columns = {"Last Name", "First Name", "Overall Average"}; // private static in the exemple
		
		
		Workbook workbookOutput = new XSSFWorkbook(); 
		
		Sheet sheet = workbookOutput.createSheet("Students");
		
		// Create a Font for styling header cells
		Font headerFont = workbookOutput.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		
		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbookOutput.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		// Create a Row
		Row headerRow = sheet.createRow(0);
		
		// Create Cells. 					Remember : columns is the Array in which you stored, as Strings, the name of the different columns
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		// go back to the tutorial if you ever need to format Dates, there's some trickery involved due to the xls xlsx duality.
		
		// Create other rows and cells with employees data
		int rowNum=1;
		for(__test__Student student: studentsPassingTheYear) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(student.getLastName());
			row.createCell(1).setCellValue(student.getFirstName());
			row.createCell(2).setCellValue(student.getOverallAverage());
		}
		
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		// The workbook object now contains all the data we're looking to push to an actual Excel File
		// Now we just need to call the method write of the wotkbook object passing it a simple Java FileOutputStream as argument
		
		FileOutputStream fos = new FileOutputStream("src/main/resources/ExcelFromJava.xlsx");	// ok using same fos name because its in a different method
		workbookOutput.write(fos);
		fos.close();
		
		// Closing the workbook
		workbookOutput.close();
	}
	
	@PostMapping("/uploadpdf")
	public ResponseEntity<String> uploadToLocalFileSystem(@RequestParam("file") MultipartFile file)
	{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// Path path = Paths.get(fileBasePath + fileName);
		
		return new ResponseEntity<String>("nothing", HttpStatus.OK);
		
	}

}
