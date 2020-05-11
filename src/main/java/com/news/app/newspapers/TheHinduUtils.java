package com.news.app.newspapers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TheHinduUtils {
	
	public void printHindu(TheHindu hinduObj) throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter("hindu.txt")) {
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
