package Crawler.control;

import java.io.File;
public class ControlClass {

	private static String URLWithoutProtocol;
	private String URLWithProtocol;
	private static int NumberOfLinksToVisit; 
	private static String proxyPort;
	private static String proxyHost;
	private String ConfigLocation;
	private String USER_AGENT;
	private static boolean testProtocol;
	private static boolean searchForWord;
	private String WordToSearch;
	private static String saveLogLocation;
	private static File ConfigFile =new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());   


	
    
    
//---------------------------------------------------Use later when package as JAR file -----------------------   
    public static String getJarDir() {
        String jarDirToString = ConfigFile.getAbsolutePath().toString();
        jarDirToString += "\\SpiderConfig.xml";
        return jarDirToString;
  }
    
    
    
    
	public static boolean isSearchForWord() {
		return false;
	}
	public static void setSearchForWord(boolean searchForWord) {
		ControlClass.searchForWord = searchForWord;
	}
	
    
    
	
	public static String getWordToSearch() {
		//return symbolmap.lookupSymbol("WordToSearch");
		return "less typing";
	}
	public void setWordToSearch(String wordToSearch) {
		WordToSearch = wordToSearch;
	}
	
	
	
	public static boolean isTestProtocol() {
		return true;
	}
	public static void setTestProtocol(boolean testProtocol) {
		ControlClass.testProtocol = testProtocol;
	}

	
	
	public String getUSER_AGENT() {
		//return symbolmap.lookupSymbol("UserAgent");
		return "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	}
	public void setUSER_AGENT(String uSER_AGENT) {
		USER_AGENT = uSER_AGENT;
	}
	
	
	
    
	public static String getConfigLocation() {
		return "C:\\Users\\bosco\\Desktop\\New folder\\SpiderConfig.xml";
	}
	public void setConfigLocation(String configLocation) {
		ConfigLocation = configLocation;
	}

	
	
	public static String getProxyPortNumber() {
		return proxyPort;
	}
	public static void setProxyPortNumber(String proxyPortNumber) {
		proxyPort = proxyPortNumber;
	}

	
	
	
	public static String getProxyHost() {
		return proxyHost;
	}
	public static void setProxyHost(String receivedproxyHost) {
		proxyHost = receivedproxyHost;
	}

	
	
	
	//======================================================================================================================================================
	public static String getURLWithoutProtocol() {
		return URLWithoutProtocol;	
	}

	public static void setURLWithoutProtocol(String uRLWithoutProtocol) {
		URLWithoutProtocol = uRLWithoutProtocol;
	}
	
	
	//======================================================================================================================================================    
    public static String getURLWithProtocol() {
		return "https://en.wikipedia.org";
	}
	public void setURLWithProtocol(String uRLWithProtocol) {
		URLWithProtocol = uRLWithProtocol;
	}
	
	
	
	//put number of total links to visit here:
	public static int getNumberOfLinksToVisit() {
		//int num = Integer.parseInt(symbolmap.lookupSymbol("NumberOfLinksToVisit"));
		return NumberOfLinksToVisit;
	}

	public static void setNumberOfLinksToVisit(int numberOfLinksToVisit) {
		NumberOfLinksToVisit = numberOfLinksToVisit;
	}

	public static void setLogLocation(String chooseBoxLocation) {
		saveLogLocation = chooseBoxLocation;
	}
	
	public static String getLogLocation() {
		//saveLogLocation = saveLogLocation;
		//return saveLogLocation;
		return new StringBuilder().append(saveLogLocation).append("\\VisitedLog.txt").toString();
	}
	
}
