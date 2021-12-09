package validate;

import ReadAndWriteFile.WriteAndReadCustomersFile;
import ReadAndWriteFile.WriteAndReadProductFile;
import module.Customer;
import module.Motors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {
    Scanner scanner = new Scanner(System.in);
    WriteAndReadCustomersFile customersFileController = new WriteAndReadCustomersFile();
    List<Customer> customerList;
    {
        try {
            customerList = customersFileController.readCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    WriteAndReadProductFile productFileController = new WriteAndReadProductFile();
    List<Motors> motorsList;

    {
        try {
            motorsList = productFileController.readMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean confirmDelete() {
        System.out.println("|*****thao tác xóa sẽ không thể hoàn tác!*****|");
        System.out.println("|      bạn chắc chắn muốn xóa mục này         |");
        System.out.println("|                1. xác nhận                  |");
        System.out.println("|                 2. hủy                      |");
        System.out.println("|*********************************************|");
        try {
            int confirm = Integer.parseInt(scanner.nextLine());
            if (confirm == 1) {
                return true;
            } else if (confirm == 2) {
                return false;
            } else {
                System.err.println("********************************************************");
                System.err.println("vui lòng nhập đúng lựa chọn trong menu");
                System.err.println("********************************************************");
                return confirmDelete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("********************************************************");
            System.err.println("vui lòng nhập đúng lựa chọn trong menu");
            System.err.println("********************************************************");
            return confirmDelete();
        }
    }
    public int checkChoiceInt(){
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            return choice;
        }catch (Exception e){
            System.err.println("********************************************************");
            System.err.println("vui lòng nhập số nguyên dương tương ứng lựa chon trong menu");
            System.err.println("********************************************************");
            return checkChoiceInt();
        }
    }
    public int quantity(){
        int quantity;
        try {
            System.out.println("số lượng");
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity < 0){
                System.err.println("********************************************************");
                System.err.println("số lương không được âm!");
                System.err.println("********************************************************");
                return quantity();
            }
            return quantity;
        }catch (Exception e){
            System.err.println("********************************************************");
            System.err.println("vui lòng nhập số nguyên dương");
            System.err.println("********************************************************");
            return quantity();
        }
    }
    public byte checkCustomersDuplication(String phoneNumbers) {
        if (customerList.size() != 0) {
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getPhoneNumber().equalsIgnoreCase(phoneNumbers)) {
                    System.out.println("********************************************************");
                    System.out.println("có phải quý khách tên là: " + customerList.get(i).getName());
                    System.out.println("1. có");
                    System.out.println("2. không");
                    System.out.println("********************************************************");
                        int choice = checkChoiceInt();
                        if (choice == 1) {
                            return 1;
                        } else {
                            return 2;
                        }
                }
            }
        }
        return 3;
    }
    public double inputAndCheckPrice(){
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
            if (price < 0){
                System.out.println("giá phải lớn hơn 0");
                return inputAndCheckPrice();
            }else return price;
        }catch (Exception e){
            System.out.println("vui lòng nhập giá đúng kiểu số thực");
            return inputAndCheckPrice();
        }
    }
    public int inputAndCheckAge(){
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
            if (age >=18 && age <= 60){
                return age;
            }else {
                System.err.println("********************************************************");
                System.err.println("chúng tôi chỉ bán xe cho người từ 18 - 60 tuổi: ");
                System.err.println("bạn có thể khai khống mà! ");
                System.err.println("********************************************************");
                return inputAndCheckAge();
            }
        }
        catch (Exception e){
            System.err.println("********************************************************");
            System.err.println("bạn vui lòng nhập đúng số tuổi");
            System.err.println("********************************************************");
            return inputAndCheckAge();
        }
    }
    public String inputAndCheckPhoneNumber(){
        String phoneNumber;
        phoneNumber = scanner.nextLine();
        System.out.println("********************************************************");
        Pattern m = Pattern.compile("^0[0-9]{9}$");
        if (m.matcher(phoneNumber).find()){
            return phoneNumber;
        }else
        {
            System.out.println("********************************************************");
            System.err.println("nhập lại số điện thoại với 10 số và bắt đầu = 0");
            System.out.println("********************************************************");
            return inputAndCheckPhoneNumber();}
    }
    public int inputAndCheckID(){
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
            if (id < 0){
                System.out.println("id phải lớn hơn 0");
               return inputAndCheckID();
            }
            return id;
        }catch (Exception e){
            System.out.println("vui lòng nhập id là số nguyên dương");
           return inputAndCheckID();
        }
    }
}
