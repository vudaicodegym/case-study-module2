package module;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private List<PurchasedProducts> purchasedProducts;

//    public Customer(String name, int age, String address, String phoneNumber, PurchasedProducts purchasedProducts) {
//
//    }

    public Customer(String name, int age, String address, String phoneNumber, List<PurchasedProducts> purchasedProducts) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.purchasedProducts = purchasedProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<PurchasedProducts> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<PurchasedProducts> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    @Override
    public String toString() {
        return "tên khách hàng: " + name + '\'' +
                ", tuổi khách hàng: " + age +
                ", địa chỉ: " + address + '\'' +
                ", số điện thoại: " + phoneNumber + '\'' +
                ", " + purchasedProducts
                ;
    }
}
