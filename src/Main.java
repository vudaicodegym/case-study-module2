import controller.AdminActivities;

import controller.CustomersAtivites;
import validate.Validate;

import java.util.Scanner;

public class Main {
    static AdminActivities adminActivities;

    static {
        try {
            adminActivities = new AdminActivities();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static CustomersAtivites customerActivities;

    static {
        try {
            customerActivities = new CustomersAtivites();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Validate validate = new Validate();
    public static void main(String[] args){
        homeMenu();
    }
    public static void homeMenu(){
        int choice;
            do {
                System.out.println("=====CHÀO MỪNG ĐẾN VỚI CỬA HÀNG MOTOR VŨ ĐẠI=====");
                System.out.println("|              1. khách hàng                    |");
                System.out.println("|                2. admin                       |");
                System.out.println("|                0. thoát                       |");
                System.out.println("|_______________________________________________|");
                choice = validate.checkChoiceInt();
                if (choice == 0 || choice == 1 || choice == 2) {
                    switch (choice) {
                        case 1:
                            customerActivities.menuCustomersActivities();
                            break;
                        case 2:
                            adminActivities.menuAdmin();
                            break;
                    }
                }
                else {
                    System.err.println("********************************************************");
                    System.err.println("làm ơn nhập đúng lựa chọn -_-");
                    System.err.println("********************************************************");
                }
            } while (choice != 0);
    }
}
