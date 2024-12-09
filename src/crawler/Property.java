package crawler;

public class Property {

    private String city;
    private String postcode;
    private String houseNumber;
    private double price;


    // Constructor to initialize property details
    public Property(String city, String postcode, String houseNumber, double price) {
        this.city = city;
        this.postcode = postcode;
        this.houseNumber = houseNumber;
        this.price = price;
    }

    // Getter and Setter methods for city
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter methods for postcode
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    // Getter and Setter methods for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayPropertyDetails() {
        System.out.println("City: " + city);
        System.out.println("Postcode: " + postcode);
        System.out.println("House Number: " + houseNumber);
        System.out.println("Price: " + price);
    }
}
