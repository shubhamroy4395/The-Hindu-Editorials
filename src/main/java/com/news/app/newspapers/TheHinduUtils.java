package com.news.app.newspapers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TheHinduUtils {
	
	public void printHindu(TheHindu hinduObj) throws FileNotFoundException {
		String defaultPath = "C:/Users/Shubham Roy/eclipse-workspace/The-Hindu-Editorials/src/main/resources/hindu.txt";
		String desktopPath = System.getProperty("user.home") + "/Desktop"+"/hindu.txt";
		try (PrintWriter out = new PrintWriter(desktopPath)) {
			out.println(hinduObj.getHinduArticles().get("firstEditorialTitle"));
			out.println("\n");
			out.println(hinduObj.getHinduArticles().get("firstEditorialArticle"));
			out.println("\n\n\n\n");
			out.println(hinduObj.getHinduArticles().get("secondEditorialTitle"));
			out.println("\n");
		    out.println(hinduObj.getHinduArticles().get("secondEditorialArticle"));
		    System.out.println("Printed articles to files successfully");
		    out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
