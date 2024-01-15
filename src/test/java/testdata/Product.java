package testdata;

import helpers.JsonReaderHelper;

public class Product {

    private String name;
    private String description;
    private double price;

    public Product(String fileName) {
        this.name = JsonReaderHelper.json(fileName).get("name").toString();
        this.description = JsonReaderHelper.json(fileName).get("description").toString();
        this.price = (double) JsonReaderHelper.json(fileName).get("price");
    }

    //GETTERS
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
