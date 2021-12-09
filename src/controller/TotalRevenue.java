package controller;

import ReadAndWriteFile.WriteAndReadCustomersFile;
import ReadAndWriteFile.WriteAndReadProductFile;
import module.Customer;
import module.Motors;

import java.util.List;
import java.util.Scanner;

public class TotalRevenue {
    WriteAndReadCustomersFile customersFileController = new WriteAndReadCustomersFile();
    WriteAndReadProductFile productFileController = new WriteAndReadProductFile();
    Scanner scanner = new Scanner(System.in);
    List<Customer> customerList;

    {
        try {
            customerList = customersFileController.readCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Motors> motorsList;

    {
        try {
            motorsList = productFileController.readMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRevenue(){
        double totalRevenue = 0;
        double revenue;
        for (int i = 0; i < customerList.size(); i++) {
            for (int j = 0; j < customerList.get(i).getPurchasedProducts().size(); j++) {
                for (int k = 0; k < motorsList.size(); k++) {
                    if (motorsList.get(k).getModel().equals(customerList.get(i).getPurchasedProducts().get(j).getModel())){
                        if ( motorsList.get(k).getColor().equals(customerList.get(i).getPurchasedProducts().get(j).getColor())){
                            revenue = customerList.get(i).getPurchasedProducts().get(j).getQuantity() * motorsList.get(k).getPrice();
                            totalRevenue += revenue;
                        }
                    }
                }
            }
        }
        System.out.println("tổng doanh thu là:" + totalRevenue);
    }
}

