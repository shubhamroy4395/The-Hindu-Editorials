package com.news.app.application;

import java.io.FileNotFoundException;

import com.news.app.newspapers.TheHindu;
import com.news.app.newspapers.TheHinduUtils;

public class Application {

	public static void main(String[] args) throws FileNotFoundException {
		TheHindu hinduObj = new TheHindu();
		TheHinduUtils printObj = new TheHinduUtils();
		printObj.printHindu(hinduObj);
				
		
		
	  }

	

	}
