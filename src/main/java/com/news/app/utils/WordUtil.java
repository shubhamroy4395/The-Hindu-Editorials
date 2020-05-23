package com.news.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Br;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

import com.news.app.constants.NewsAppConstants;

public class WordUtil {
	 private static ObjectFactory objectFactory = new ObjectFactory();
	private final static Logger LOG =   Logger.getLogger(WordUtil.class); 
	
	public void writeToWord( Map<String, String> newsMap)throws InvalidFormatException, Docx4JException {
	  String desktopPath = System.getProperty("user.home") + "/Desktop";
	  
	  DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("ddMMyyyy");
      String timeForFile = timeStampPattern.format(LocalDateTime.now());
      String fileName = "/The_Hindu_Editorial_"+timeForFile+".docx";
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
	

}
