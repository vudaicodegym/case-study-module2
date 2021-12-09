package module;

import java.io.Serializable;

public class PurchasedProducts implements Serializable {
    private String model;
    private String color;
    private int quantity;

    public PurchasedProducts(String model, String color, int quantity) {
        this.model = model;
        this.color = color;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sản phẩm đã mua:" +
                "" + model +
                ", màu: '" + color + '\'' +
                ", số lượng đã mua: " + quantity ;
    }
}
