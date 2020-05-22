package com.news.app.application;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import com.news.app.newspapers.TheHindu;
import com.news.app.utils.WordUtil;

public class Application {
	
	private final static Logger LOG =  Logger.getLogger(Application.class); 
	public static void main(String[] args) throws IOException {
		  LOG.info("Main Application Started");
		  WordUtil wordUtil = new WordUtil();
		  Map<String, String> hinduObj = new TheHindu().getHinduArticles(); 
		  try {
			wordUtil.writeToWord(hinduObj);
		    } catch (Docx4JException e) {
			LOG.error("Error in writing to the Doc file",e);
		}
		  LOG.info("Main Application Completed");
		
	  }



	}
