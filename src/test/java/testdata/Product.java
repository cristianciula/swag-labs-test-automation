package testdata;

import helpers.JSONReaderHelper;

public class Product {

    private String name;
    private String description;
    private double price;

    public Product(String fileName) {
        this.name = JSONReaderHelper.extractValue(fileName, "name");
        this.description = JSONReaderHelper.extractValue(fileName, "description");
        this.price = Double.parseDouble(JSONReaderHelper.extractValue(fileName, "price"));
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
