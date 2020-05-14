package com.news.app.newspapers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.news.app.constants.NewsAppConstants;

public class TheHindu {

	public  Map<String, String> getHinduArticles(){
		Map<String, String> hinduArticlesMap = new HashMap<String, String>();
		Document doc;
	    try {
	        doc = getDocument(NewsAppConstants.HINDU_URL);
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
		  Elements links = doc.select(NewsAppConstants.HINDU_FIRST_ARTICLE_URL_CSS_SELECTOR);
	      return links.attr(NewsAppConstants.HREF);
		
	}
	
	private String getSecondLink(Document doc) {
		Elements links = doc.select(NewsAppConstants.HINDU_SECOND_ARTICLE_URL_CSS_SELECTOR);
	      return links.attr(NewsAppConstants.HREF);
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
	        firstEditorialMap.put(NewsAppConstants.FIRST_ARTICLE_TITLE, firstEditorialLinkDoc.title());
	        Elements editlinks = firstEditorialLinkDoc.select(NewsAppConstants.HINDU_CONTENT_BODY_CSS_SELECTOR);
	        article =editlinks.select("p").text();
	        firstEditorialMap.put(NewsAppConstants.FIRST_ARTICLE_BODY,article);
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
	        secondEditorialMap.put(NewsAppConstants.SECOND_ARTICLE_TITLE, secondEditorialLinkDoc.title());
	        Elements editlinks = secondEditorialLinkDoc.select(NewsAppConstants.HINDU_CONTENT_BODY_CSS_SELECTOR);
	        article =editlinks.select("p").text();
	        secondEditorialMap.put(NewsAppConstants.SECOND_ARTICLE_BODY,article);
		}
	        return secondEditorialMap;
		
	}
	
	private Document getDocument(String url) throws IOException {
		return Jsoup.connect(url)
	    	     .userAgent(NewsAppConstants.JSOUP_USER_AGENT)
	    	     .get();
	}

}

