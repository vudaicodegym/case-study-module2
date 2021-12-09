package module;

import java.io.Serializable;

public class Motors implements Serializable {
    private int id;
    private String brand;
    private String model;
    private String color;
    private double cylinderCapacity;
    private String category;
    private double price;
    private int inventory ;
    public Motors(String model, String color, int quantity) {
    }

    public Motors(int id, String brand, String model, String color, double cylinderCapacity, String category, double price, int inventory) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.cylinderCapacity = cylinderCapacity;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getInventory(){
        return inventory;
    }
    public void setInventory(){
        this.inventory = inventory;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(double cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "mã sản phẩm: " + id+
                ", hãng: " + brand +
                ", tên xe: " + model +
                ", màu sắc: " + color +
                ", dung tích xy lanh: " + cylinderCapacity +
                ", dòng xe: " + category +
                ", giá: " + price +
                ", tồn kho: " + inventory +
                '}';
    }
}
