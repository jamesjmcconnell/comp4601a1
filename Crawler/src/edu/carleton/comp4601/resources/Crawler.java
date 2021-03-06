package edu.carleton.comp4601.resources;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClient;

import org.bson.Document;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawler extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");
    MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase database = mongoClient.getDatabase("SDADB");
	MongoCollection<org.bson.Document> collection = database.getCollection("docs");
	
    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
//        Ignore the url if it has an extension that matches our defined set of image extensions.
//        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
//            return false;
//        }

        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
        return href.startsWith("https://sikaman.dyndns.org/courses/4601/");
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
    	
        Document doc = new Document();
    	
        int docid = page.getWebURL().getDocid();
        doc.put("docid", docid);
        
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();
        


        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            
            String text = htmlParseData.getText();
            doc.put("text", text);
            String html = htmlParseData.getHtml();
            org.jsoup.nodes.Document htmlDocument = Jsoup.parse(html);
            String name = htmlDocument.getElementsByTag("title").get(0).text();
            doc.put("name", name);
            doc.put("url", url);
            doc.put("score", (float)0);
            Elements tagString = htmlDocument.select("p");
            String[] tags = tagString.text().split(" ");
            Set<String> uniqueTags = new HashSet<String>();
            for(int i = 0; i < tags.length; i++){
            	uniqueTags.add(tags[i]);
            }
            doc.put("tags", uniqueTags);
            
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            ArrayList<String> formattedLinks = new ArrayList();
            for(WebURL l : links){
                formattedLinks.add(l.toString());
            }
            
            doc.put("links", formattedLinks);
        	collection.insertOne(doc);
        }else{logger.debug("!@# PAGE WAS NOT INSTANCE OF htmlParseData");}

        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            logger.debug("Response headers:");
            for (Header header : responseHeaders) {
                logger.debug("\t{}: {}", header.getName(), header.getValue());
            }
        }

        logger.debug("=============");
    }
}
