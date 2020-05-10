package com.news.app.newspapers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TheHindu {

	public  Map<String, String> getHinduArticles(){
		Map<String, String> hinduArticlesMap = new HashMap<String, String>();
		String editorialURL = "https://www.thehindu.com/opinion/editorial/";
		Document doc;
	    try {
	        doc = getDocument(editorialURL);
	        String firstURL = getFirstLink(doc);
	        hinduArticlesMap.putAll(getFirstEditorial(firstURL));
	        
	        String secondUrl = getSecondLink(doc);
	        hinduArticlesMap.putAll(getSecondEditorial(secondUrl));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return hinduArticlesMap;
	}
	
	private String getFirstLink(Document doc) {
		  Elements links = doc.select("body > div.container-main > section:nth-child(3) > section.feature-news > div > div > div > div > div > div > div:nth-child(3) > h2 > a");
	      return links.attr("href");
		
	}
	
	private String getSecondLink(Document doc) {
		Elements links = doc.select("body > div.container-main > section:nth-child(3) > section.feature-news > div > div > div > div > div > div > div:nth-child(4) > h2 > a");
	      return links.attr("href");
	}
	
	private Map<String, String> getFirstEditorial(String firstEditorialLink) {
		Map<String, String> firstEditorialMap = new HashMap<String, String>();
		  Document firstEditorialLinkDoc = null;
		  String article = "";
		try {
			firstEditorialLinkDoc = getDocument(firstEditorialLink);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(firstEditorialLinkDoc!=null) {
	        String firstEditorialLinkTitle = firstEditorialLinkDoc.title();
	        firstEditorialMap.put("firstEditorialTitle", firstEditorialLinkTitle);
	        Elements editlinks = firstEditorialLinkDoc.select("div[id^=content-body]");
	        article =editlinks.select("p").text();
	        firstEditorialMap.put("firstEditorialArticle",article);
		}
	        return firstEditorialMap;
	}
	
	private Map<String, String> getSecondEditorial(String url) {
		Map<String, String> secondEditorialMap = new HashMap<String, String>();
		  Document secondEditorialLinkDoc = null;
		  String article = "";
		try {
			secondEditorialLinkDoc = getDocument(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(secondEditorialLinkDoc!=null) {
	        String secondEditorialLinkTitle = secondEditorialLinkDoc.title();
	        secondEditorialMap.put("secondEditorialTitle", secondEditorialLinkTitle);
	        Elements editlinks = secondEditorialLinkDoc.select("div[id^=content-body]");
	        article =editlinks.select("p").text();
	        secondEditorialMap.put("secondEditorialArticle",article);
		}
	        return secondEditorialMap;
		
	}
	
	private Document getDocument(String url) throws IOException {
		return Jsoup.connect(url)
	    	     .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
	    	     .get();
	}

}

