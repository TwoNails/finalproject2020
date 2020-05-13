package co.simplon.finalproject2020.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.repository.__AttachedDocumentRepository;
import co.simplon.finalproject2020.storage.StorageService;

@RestController
@RequestMapping("/upndownload")
@CrossOrigin(origins = "http://localhost:4200")
public class __FileUploadController {
	
	// private final StorageService storageService;
	
	/*
	@Autowired
	public __FileUploadController() {
		// TODO Auto-generated constructor stub
	}
	*/
	
	/*
	@Autowired
	public __FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
	*/
//	@Autowired
//	private __AttachedDocumentRepository attachedDocumentRepository;
//	
//	
//	@GetMapping("/{demandeId}")
//	public List <String> listUploadedFiles (@PathVariable int demandeId) {
//		
//		List<AttachedDocument> listStoredFiles = attachedDocumentRepository.findAllByFileReference(demandeId);
//		List<String> listOfFileNames = new ArrayList<String>();
//		
//		for(AttachedDocument doc : listStoredFiles) {
//			listOfFileNames.add(doc.getName());
//		}	
//		return listOfFileNames;
//	}
	
	
//	@GetMapping("/{demandeId}/{filename}")
//	@ResponseBody
//	public ResponseEntity<ByteArrayResource> serveFile(@PathVariable int demandeId, @PathVariable String filename){
//
//		List<AttachedDocument> doc = attachedDocumentRepository.findByFileReferenceAndName(demandeId, filename);
//		
//		if(doc.size() !=1) {
//			System.out.println("something is wrong either with the request or the base " + doc.size());
//			return null;
//		} else {
//			AttachedDocument docInBase = doc.get(0);
//			return ResponseEntity.ok()
//					.contentType(MediaType.parseMediaType(docInBase.getFileExtension()))
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +docInBase.getName() + "\"")
//					.body(new ByteArrayResource(docInBase.getContent()));
//		}
//	}
	
	@PostMapping("/{demandeId}")
	public void handleFileUpload(@PathVariable int demandeId, @RequestParam("file") MultipartFile file /*,  RedirectAttributes redirectAttributes */) {
		
		/* TESTS : SAVE FILE FROM HTTP REQUEST (POSTMAN) IN DATABASE */
		
		try {
			// AttachedDocument docToSave = new AttachedDocument(file.getOriginalFilename(), file.getContentType(), demandeId, file.getBytes());
			
			// attachedDocumentRepository.saveAndFlush(docToSave);
		} catch (/*IO*/Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* TESTS : READING DATABASE AND CREATING IN LOCAL FILES A COPY OF THE DOCUMENT FROM THE DATA FETCHED */
		
//		Optional<AttachedDocument> copyFromDataBase = attachedDocumentRepository.findById(1);
//		if(copyFromDataBase.isPresent()) {
//			AttachedDocument documentToCopy = copyFromDataBase.get();
//			File copy = new File("src/main/resources/" + documentToCopy.getName());		// we describe the route where we want to build the file
//			FileOutputStream fos2;
//			try {
//				fos2 = new FileOutputStream(copy);
//				fos2.write(documentToCopy.getContent());
//				fos2.flush();
//				fos2.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}
	
}
