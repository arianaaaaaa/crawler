package crawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebCrawler {
    public List<Property> scrapeListings(String url) {
        List<Property> data = new ArrayList<>();

        // Set the path to the ChromeDriver executable (make sure it's installed)
        System.setProperty("webdriver.chrome.driver", "/Users/ariana.vargas/Downloads/chromedriver/chromedriver");  // Update this path

        // Initialize WebDriver (here, we're using ChromeDriver)
        WebDriver driver = new ChromeDriver();

        try {
            List<List<WebElement>> links = new ArrayList<>();

            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Adjust the timeout as needed
            links.add(wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a.text-secondary-70"))));

            List<WebElement> flattenedList = links.stream()
                    .flatMap(List::stream) // Flatten each inner list
                    .collect(Collectors.toList()); // Collect into a single list

            List<String> urls = new ArrayList<>();

            for (WebElement link : flattenedList) {
                String href = link.getAttribute("href");

                // Check if the URL is not null and contains 'funda.nl/detail' (indicating it's a property URL)
                if (href != null && href.contains("funda.nl/detail")) {
                    urls.add(href);
                }
            }
            //to fix add access to other pages
//            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement nextButton = wait2.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//a[span[contains(text(),'Volgende')]]")
//                    ));
//            nextButton.click();
//            System.out.println("Next button clicked.");
//
//            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait3.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
//
//            String nextPageURL = nextButton.getAttribute("href");
//            System.out.println("Next page URL: " + nextPageURL);

            for (String urlListing : urls) {
                System.out.println(urlListing);

                WebDriver driverListings = new ChromeDriver();
                driverListings.get(urlListing);

                WebDriverWait wait1 = new WebDriverWait(driverListings, Duration.ofSeconds(10));
                WebElement priceElement = wait1.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("div.flex.gap-2.font-bold span")));

                WebDriverWait wait2 = new WebDriverWait(driverListings, Duration.ofSeconds(3));
                WebElement neighborhoodDiv = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[city='Amsterdam']")));

                String city = neighborhoodDiv.getAttribute("city");
                String postcode = neighborhoodDiv.getAttribute("postcode");
                String houseNumber = neighborhoodDiv.getAttribute("housenumber");
                double price = Double.parseDouble(priceElement.getAttribute("innerHTML").replaceAll("[^0-9]", ""));
                data.add(new Property(city, postcode, houseNumber, price));
                driverListings.quit();
            }

        } finally {
            // Close the WebDriver
            driver.quit();
        }

        return data; // Return the scraped data
    }
}
