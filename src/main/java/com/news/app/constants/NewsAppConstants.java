package com.news.app.constants;

public final class NewsAppConstants {

	private NewsAppConstants() {}
	public static final String HINDU_URL = "https://www.thehindu.com/opinion/editorial/";
	public static final String HINDU_FIRST_ARTICLE_URL_CSS_SELECTOR = "body > div.container-main > section:nth-child(3) > section.feature-news > div > div > div > div > div > div > div:nth-child(3) > h2 > a";
	public static final String HINDU_SECOND_ARTICLE_URL_CSS_SELECTOR = "body > div.container-main > section:nth-child(3) > section.feature-news > div > div > div > div > div > div > div:nth-child(4) > h2 > a";
	public static final String HREF = "href";
	public static final String HINDU_CONTENT_BODY_CSS_SELECTOR = "div[id^=content-body]";
	public static final String FIRST_ARTICLE_TITLE = "firstEditorialTitle";
	public static final String FIRST_ARTICLE_BODY = "firstEditorialArticle";
	public static final String SECOND_ARTICLE_TITLE ="secondEditorialTitle";
	public static final String SECOND_ARTICLE_BODY ="secondEditorialArticle";
	public static final String HINDU_RSS_URL = "https://www.thehindu.com/opinion/editorial/feeder/default.rss";
	public static final String LINK = "link";
	public static final String FIRST_RSS_URL = "firstURL";
	public static final String SECOND_RSS_URL = "secondUrl";
	public static final String FIRST_RSS_PUBLISH_DATE = "firstPublishDate";
	public static final String SECOND_RSS_PUBLISH_DATE = "secondPublishDate";
	public static final String PUB_DATE = "pubdate";
	
	
	
	/***** JSOUP CONFIG***********/
	public static final String JSOUP_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
	
	
	
}
