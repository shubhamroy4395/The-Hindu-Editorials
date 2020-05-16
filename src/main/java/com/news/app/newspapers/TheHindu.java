package com.news.app.newspapers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.news.app.constants.NewsAppConstants;

public class TheHindu {
	
	public static Map<String, String> getUrlFromRss() throws IOException {
		Map<String, String> hinduUrlMap =  new HashMap<String, String>();
		Document docRss = getDocument("https://www.thehindu.com/opinion/editorial/feeder/default.rss");
		String myJXml = docRss.toString();
		JSONObject jObject = XML.toJSONObject(myJXml);
		JSONArray ob = jObject.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
		
		String firstUrl = ob.getJSONObject(0).getString("link");
		String secondUrl = ob.getJSONObject(1).getString("link");
		hinduUrlMap.put("firstURL", firstUrl);
		hinduUrlMap.put("secondUrl", secondUrl);
		return hinduUrlMap;	
	}

	public  Map<String, String> getHinduArticles() throws IOException{
		Map<String, String> hinduArticlesMap = new HashMap<String, String>();
		Map<String, String> urlMap = getUrlFromRss();
		Document doc;
	    try {
	        doc = getDocument(NewsAppConstants.HINDU_URL);
	        String firstURL = urlMap.get("firstURL");     //getLink(doc,NewsAppConstants.HINDU_FIRST_ARTICLE_URL_CSS_SELECTOR);
	        hinduArticlesMap.putAll(getFirstEditorial(firstURL));
	        
	        String secondUrl = urlMap.get("secondUrl");                //getLink(doc,NewsAppConstants.HINDU_SECOND_ARTICLE_URL_CSS_SELECTOR);
	        hinduArticlesMap.putAll(getSecondEditorial(secondUrl));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return hinduArticlesMap;
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
	
	private static Document getDocument(String url) throws IOException {
		return Jsoup.connect(url)
	    	     .userAgent(NewsAppConstants.JSOUP_USER_AGENT)
	    	     .get();
	}

}

