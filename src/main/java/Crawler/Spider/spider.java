package Crawler.Spider;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Crawler.control.ControlClass;

public class spider 
{
	writeClass writeObj = new writeClass();
	
	private static final int MAX_PAGES_TO_SEARCH = ControlClass.getNumberOfLinksToVisit();
	//HashSet cannot contain duplicate elements while lists can
	private static Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private static boolean testProtocol = false;
	
	
    public static void main(String[] args )
    {
    	spider spider = new spider();

    	
    	//if testProtocol = true, try with http and https, otherwise, follow predefined protocol
    	if (ControlClass.isTestProtocol()) {
    	//tries both http and https protocol to determine which one to use:
    	try {
			if (CrawlWork.usesHttps(ControlClass.getURLWithoutProtocol())) {
				System.out.println("Using HTTPS");		    
		    	//last arg specifies to search or not, false means don't search for word
		    	spider.search("https://" + ControlClass.getURLWithoutProtocol()+ "/", "Less Typing", ControlClass.isSearchForWord());

			}
			else
				System.out.println("Using HTTP");					    	
	        	spider.search("http://" + ControlClass.getURLWithoutProtocol() + "/", "Less Typing", ControlClass.isSearchForWord());


			} catch (IOException protocol) {
			// TODO Auto-generated catch block
			protocol.printStackTrace();
			}
    	}
    	//if not testing protocol, provide link with protocol:
    	else spider.search(ControlClass.getURLWithProtocol(), "Less Typing", ControlClass.isSearchForWord());
    }
    
    
    
    
    
    //pagesToVisit gets the first entry, check if it's visited.
    //keep looping through the list of pagesToVisit and return next URL
    private String nextUrl()
    {
    	String nextURL;
    	
    	do{
    		//removes first node, 2nd node becomes the nextURL
    		nextURL = this.pagesToVisit.remove(0);
    		//do this while not reaching the end:
    	}while(this.pagesVisited.contains(nextURL));
    	
    	this.pagesVisited.add(nextURL);
    	return nextURL;
    }
    

    

    
    public void search(String url, String searchWord, boolean searchforword)
    {	//first  parm is the pages actually obtained, second param is the amount of attempts to get links, regardless of actually receiving links or not:
    	while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH && CrawlWork.CurrentLinkNumber < ControlClass.getNumberOfLinksToVisit())
    	{
    		String currentURL;
    		CrawlWork crawler = new CrawlWork();
    		
    		//if no pages to visit, set the function URL to the current URL and mark it as visited
    		if(this.pagesToVisit.isEmpty()) {
    			currentURL = url;
    			this.pagesVisited.add(url);
    		}
    		else {
    			//update the current URL with the String fetched from the method
    			currentURL = this.nextUrl();
    		}
    		
    		//crawl passing in the current URL
    		crawler.crawl(currentURL, CrawlWork.CurrentLinkNumber);
    		
    		
    		//option to search for a particular word or just crawl endlessly
    		if (searchforword) 
    		{
    			System.out.println("mode is set to search for particular keyword: ");
    			boolean success = crawler.searchForWord(searchWord);
    			
    			if (success) {
    				System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentURL));
    				break;
    			}
    		//add all links on current site to pagesToVisit List
    		this.pagesToVisit.addAll(crawler.getLinks());
    		}
    		
    		else  	
    			this.pagesToVisit.addAll(crawler.getLinks());
    		writeObj.appendContent(this.pagesVisited.size(), currentURL);
    	}
    	System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
    }
    
}