package Crawler.Spider;

public class threadClass implements Runnable{

	spider spider = new spider();

	@Override
	public void run() {
		spider.main(null);
	}
	
}
