package com.news.app.utils;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Br;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;

import com.news.app.constants.NewsAppConstants;

public class WordUtil {
	 private static ObjectFactory objectFactory = new ObjectFactory();
	private final static Logger LOG =   Logger.getLogger(WordUtil.class); 
	
	public void writeToWord( Map<String, String> newsMap)throws InvalidFormatException, Docx4JException {
	  String desktopPath = System.getProperty("user.home") + "/Desktop";
	  String fileName = "/The_Hindu_Editorial.docx";
      WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
      LOG.info("Writing to doc file started");

      wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", newsMap.get(NewsAppConstants.FIRST_ARTICLE_TITLE));
      wordMLPackage.getMainDocumentPart().addParagraphOfText(newsMap.get(NewsAppConstants.FIRST_RSS_PUBLISH_DATE));
      wordMLPackage.getMainDocumentPart().addParagraphOfText(newsMap.get(NewsAppConstants.FIRST_ARTICLE_BODY));


      //add next page 
      addPageBreak(wordMLPackage.getMainDocumentPart());

      wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", newsMap.get(NewsAppConstants.SECOND_ARTICLE_TITLE));
      wordMLPackage.getMainDocumentPart().addParagraphOfText(newsMap.get(NewsAppConstants.SECOND_RSS_PUBLISH_DATE));
      wordMLPackage.getMainDocumentPart().addParagraphOfText(newsMap.get(NewsAppConstants.SECOND_ARTICLE_BODY));


      wordMLPackage.save(new java.io.File(desktopPath+fileName));
	}
	
	 private static void addPageBreak(MainDocumentPart documentPart) {
	        P p = objectFactory.createP();
	        // Create object for r
	        R r = objectFactory.createR();
	        p.getContent().add(r);
	        // Create object for br
	        Br br = objectFactory.createBr();
	        r.getContent().add(br);
	        br.setType(org.docx4j.wml.STBrType.PAGE);
	        documentPart.addObject(p);
	    }
	
	
	
	
	
	
	
//	public void writeToWord( Map<String, String> newsMap) {
//		
//		  String desktopPath = System.getProperty("user.home") + "/Desktop";
//		  String fileName = "/The_Hindu.docx";
//		  
//		  WordprocessingMLPackage wordPackage = null;
//		  
//			try {
//				LOG.info("Word document creation started");
//				wordPackage = WordprocessingMLPackage.createPackage();
//			} catch (InvalidFormatException e) {
//				LOG.error("Could not initialize WordprocessingMLPackage", e);
//			}
//		
//				MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
//				mainDocumentPart.addStyledParagraphOfText("Title", newsMap.get(NewsAppConstants.FIRST_ARTICLE_TITLE));
//				mainDocumentPart.addParagraphOfText(newsMap.get(NewsAppConstants.FIRST_ARTICLE_BODY));
//			
//				mainDocumentPart.addStyledParagraphOfText("Title", newsMap.get(NewsAppConstants.SECOND_ARTICLE_TITLE));
//				mainDocumentPart.addParagraphOfText(newsMap.get(NewsAppConstants.SECOND_ARTICLE_BODY));
//				
//				File exportFile = new File(desktopPath+fileName);
//			try {
//					wordPackage.save(exportFile);
//					LOG.info("\nWord file succesfully exported to "+exportFile.getAbsolutePath());
//				
//				} catch (Docx4JException e) {
//					
//					LOG.error("Error in exporting word file to the path", e);
//				}
//	}
	
	

}
