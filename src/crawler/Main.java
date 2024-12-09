package crawler;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        crawler.WebCrawler crawler = new crawler.WebCrawler();
        System.out.println("Web Crawler Initialized!");
        String url = "https://www.funda.nl/zoeken/koop?selected_area=%5B%22amsterdam%2Fcornelis-troostbuurt%22%5D";

        List<Property> listings = crawler.scrapeListings(url);

        // Save the data to a CSV file
        if (!listings.isEmpty()) {
            String filePath = "/Users/ariana.vargas/Documents/Ariana/Crawler/src/crawler/properties.xlsx";

            SaveToExcel.saveToExcel(listings, filePath);
        } else {
            System.out.println("No listings found.");
        }
    }
}
