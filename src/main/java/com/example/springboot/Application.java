package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import java.io.File;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.RFonts;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.safety.Whitelist;

import org.w3c.tidy.Tidy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@SpringBootApplication
public class Application {

	public static void main(String[] args)  throws Exception{

		ApplicationContext ctx = SpringApplication.run(Application.class, args);

			try {

				// clean html  problem/solution
				// change style text-decoration-line / text-decoration
				// change     &nbsp;   /  add  <!DOCTYPE html [<!ENTITY nbsp '&#160;'>]>;
				// change font size  font-size: x-large  / 2em 
				// change text-font  "Arial Black" /  replace por " " or por Arial Black


				String filePath = "z.html";
				String htmlInString = readAllBytesJava7(filePath);
				String htmlCleaned = htmlInString.replace("text-decoration-line", "text-decoration");
				htmlCleaned = htmlInString.replace("\"Arial Black\"", " ").replace("x-large", "2em");
				// htmlCleaned = htmlInString.replace("x-large", "2em");

				try {
					FileWriter myWriter = new FileWriter("z2.html");
					myWriter.write(htmlCleaned);
					myWriter.close();
					System.out.println("Successfully wrote to the file.");
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}



				File myObj = new File("a.html");//item a
				File myObj3 = new File("b2.html"); //item c
				File myObj4 = new File("z2.html"); // documento de prueba final
				File myObj5 = new File("ab2.html"); 
				File myObj6 = new File("c2.html"); 



				// Scanner myReader = new Scanner(myObj);
				// String content="";
				// while (myReader.hasNextLine()) {
				// 	String data = myReader.nextLine();
				// 	// System.out.println(data);
				// 	content=content+data;
				// }

				
				Document document = Jsoup.parse(myObj4, "UTF-8", "");
				document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
				String strClean = document.html();

				// para agregar mas htmls

				// Document document2 = Jsoup.parse(myObj2, "UTF-8", "");
				// document2.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
				// Document document3 = Jsoup.parse(myObj3, "UTF-8", "");
				// document3.outputSettings().syntax(Document.OutputSettings.Syntax.xml);


				// String strClean2 = document2.html();
				// String strClean3 = document3.html();

				// String xhtml= strClean + strClean2 + strClean3 ;
								
					
				String xhtml = "<!DOCTYPE html [<!ENTITY nbsp '&#160;'>]>" + strClean;

				WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			

					
				XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
							

				wordMLPackage.getMainDocumentPart().getContent().addAll( 
							XHTMLImporter.convert( xhtml, null) );
				
				System.out.println(
						XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));
						
				wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/z2.docx") );
				

		
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

	//Read file content into string with - Files.readAllBytes(Path path)

	private static String readAllBytesJava7(String filePath) 
	{
			String content = "";

			try
			{
					content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
			} 
			catch (IOException e) 
			{
					e.printStackTrace();
			}

			return content;
	}


	
}
