package com.news.app.newspapers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.news.app.constants.NewsAppConstants;

public class TheHinduUtils {
	
	public void printHindu(TheHindu hinduObj) throws IOException {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		Map<String, String> map = hinduObj.getHinduArticles();
		
		String defaultPath = "C:/Users/Shubham Roy/eclipse-workspace/The-Hindu-Editorials/src/main/resources/hindu.txt";
		String desktopPath = System.getProperty("user.home") + "/Desktop"+"/hindu.txt";
		System.out.println("Printing articles started...");
		try (PrintWriter out = new PrintWriter(desktopPath)) {
			out.println("created at : "+dateTime.format(formatter));
			out.println("\n");
			out.println(map.get(NewsAppConstants.FIRST_ARTICLE_TITLE));
			out.println("\n");
			out.println(map.get(NewsAppConstants.FIRST_ARTICLE_BODY));
			out.println("\n\n\n\n");
			out.println(map.get(NewsAppConstants.SECOND_ARTICLE_TITLE));
			out.println("\n");
		    out.println(map.get(NewsAppConstants.SECOND_ARTICLE_BODY));
		    out.close();
		    System.out.println("Printed articles to files successfully");
		   
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
