package Crawler.Spider;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Crawler.control.ControlClass;


//jsoup has support for http request and parsing
public class CrawlWork {
	
	//below is random user found online, tricks server to think crawler is a real browser:
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new LinkedList<String>(); //list of URLS
	private Document htmlDocument; //parsed webpage, can be shared with cookied connection or without---
	public static int CurrentLinkNumber = 0;

	
	
	public boolean crawl(String url, int depth)
	{
		this.CurrentLinkNumber = depth;
		
		try
		{
			//configure with proxy settings
			System.setProperty("http.proxyHost", "127.0.0.1");
			System.setProperty("http.proxyPort", "8080");
			
			//if cookie value is received, follow these connection procedures to pass cookie in the request as well:
			/*
			if(SMFTObj.Cookievalue != null) {
				//System.out.println("CheckPoint from CrawlWork Cookie=====================================================================================" + SMFTObj.Cookievalue);
				Connection CookiedConnection = Jsoup.connect(url).userAgent(USER_AGENT);
				Document htmlDocument = CookiedConnection.cookie("JSESSIONID", SMFTObj.Cookievalue).get();
				this.htmlDocument = htmlDocument;
				System.out.println("Received web page at " + url + " Current Link Visited: " + CurrentLinkNumber++);
				if(CookiedConnection.response().statusCode() == 200) // 200 is the HTTP OK status code                 
				 	{
					 	System.out.println("\n**Visiting** link at " + url);
				 	}
				 
				 	if(!CookiedConnection.response().contentType().contains("text/html"))
				 		{
				 			System.out.println("**Failure** Did not Retrieve HTML or Text");
				 			return false;
				 		}
				
			}
			*/		
			//establish connection and parse the document 
			
			//proxy
			System.setProperty("http.proxyHost", ControlClass.getProxyHost());
			System.setProperty("http.proxyPort", ControlClass.getProxyPortNumber());
			
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);//user agent defines the browser so server won't think this is a bot
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			System.out.println("Received web page at " + url + " Current Link Visited: " + CurrentLinkNumber++);

			 if(connection.response().statusCode() == 200) // 200 is HTTP "OK" status code                 
			 	{
				 	System.out.println("\n**Visiting** link at " + url);
			 	}
			 
			 	if(!connection.response().contentType().contains("text/html"))
			 		{
			 			System.out.println("**Failure** Did not Retrieve HTML or Text");
			 			return false;
			 		}
			
			
			
			
			
			
			 	//we can limit our base URL scope here by something like this:
			 	//document.select("a[href^=\"http://www.bose.com/qc35/\"]")
//--------------//this limits us to anything with a base URl of^^ www.bose.com/qc35-----------------------------
			 	//selecting only the links with 
	            Elements linksOnPage = htmlDocument.select("a[href]");
	            System.out.println("Found (" + linksOnPage.size() + ") links");
	            for(Element link : linksOnPage)
	            {
	                this.links.add(link.absUrl("href"));	                
	            }
	            return true;	            
	        }
		
				
		catch(IOException io)
		 {
            // not successful in HTTP request
            System.out.println("DETECTED ERROR in HTTP request " + io);
        }
		return false;
	}
	
	
	
		
	
	//return if particular word is found or not
	//call this method if crawl was successful
	public boolean searchForWord(String searchWord) 
	{
	if (this.htmlDocument == null) {
		System.out.println("ERROR, crawler not called or page non existent");
		return false;
	}
		System.out.println("Searching for word: " + searchWord);
	    String bodyText = this.htmlDocument.body().text();
	    return bodyText.toLowerCase().contains(searchWord.toLowerCase());
	}
	
	
	
	
	//eventually make this part run on different thread so it takes less time, as we are going to have to try each individual link...
	//method to find out if link is HTTP or HTTPS, if return true it is HTTPS
	static boolean usesHttps(final String urlWithoutProtocol) throws IOException
	{
		try {
			System.setProperty("http.proxyHost", ControlClass.getProxyHost());
			System.setProperty("http.proxyPort", ControlClass.getProxyPortNumber());
			Jsoup.connect("https://" + ControlClass.getURLWithoutProtocol()).get();
				return true;
			} catch (final IOException e) {
        Jsoup.connect("http://" + ControlClass.getURLWithoutProtocol()).get();
        return false;
			}
	}
	

	
	
	
	
	
	public List<String>getLinks() {

		return this.links;
	}
}
