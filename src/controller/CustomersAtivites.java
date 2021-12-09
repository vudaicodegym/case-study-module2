package controller;

import ReadAndWriteFile.WriteAndReadCustomersFile;
import ReadAndWriteFile.WriteAndReadProductFile;
import module.Customer;
import module.Motors;
import module.PurchasedProducts;
import validate.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomersAtivites {
    Scanner scanner = new Scanner(System.in);
    Validate validate = new Validate();
    WriteAndReadProductFile productFileController = new WriteAndReadProductFile();
    AdminActivities adminActivities;
    WriteAndReadCustomersFile customersFileController = new WriteAndReadCustomersFile();
    List<Customer> customerList = customersFileController.readCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt");
    {
        try {
            adminActivities = new AdminActivities();
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

    public CustomersAtivites() throws Exception {
    }

    public void menuCustomersActivities(){
        int choice;
        try {
        do {
            adminActivities.showMotors();
            System.out.println(" _______VŨ ĐẠI MOTOR SHOW KÍNH CHÀO QUÝ KHÁCH_______");
            System.out.println("|              1. tìm sản phẩm theo mục:            |");
            System.out.println("|                     2. mua hàng                   |");
            System.out.println("|                      0. thoát                     |");
            System.out.println("|___________________________________________________|");
            choice = validate.checkChoiceInt();
            if (choice >= 0 && choice <3){
            switch (choice) {
                case 1: finProductMenu();
                    break;
                case 2: purchase();
                    break;
            }
            }else {
                System.err.println("vui lòng nhập đúng lựa chọn trong menu!");
            }
        }while (choice != 0);
    }catch (Exception e) {
            System.err.println("********************************************************");
            System.err.println("vui lòng nhập đúng số nguyên dương trong lựa chọn menu");
            System.err.println("********************************************************");
        }
    }

    public void finProductMenu(){
        int choice = 0;
        try {
            do {
                System.out.println("**********bạn muốn tìm sản phẩm?*********");
                System.out.println("|            1. tìm theo hãng           |");
                System.out.println("|             2. tìm theo tên           |");
                System.out.println("|          3. tìm theo màu sơn xe       |");
                System.out.println("|      4. tìm theo phân khúc động cơ    |");
                System.out.println("|           5. tìm theo tầm giá         |");
                System.out.println("|          6. tìm theo kiểu dáng xe     |");
                System.out.println("|                0. thoát               |");
                System.out.println("*****************************************");
                choice = validate.checkChoiceInt();
                if (choice >= 0 && choice <= 6) {
                    switch (choice) {
                        case 1:
                            findByBrand();
                            break;
                        case 2:
                            findByModel2();
                            break;
                        case 3:
                            findProductByColor();
                            break;
                        case 4:
                            finProductByCylinderCapacity();
                            break;
                        case 5:
                            findProductByPrice();
                            break;
                        case 6:
                            findProductByCategory();
                            break;
                    }
                } else {
                    System.err.println("********************************************************");
                    System.err.println("bạn nhập sai lựa chọn");
                    System.err.println("********************************************************");
                }
            } while (choice != 0);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("********************************************************");
            System.err.println("hệ thống gặp sự cố hoặc do bạn thao tác sai.");
            System.err.println("chúng tôi rất tiếc về sự cố này!");
            System.err.println("bạn có thể thử lại! ");
            System.err.println("********************************************************");
        }

    }
    //1. tìm theo hãng
    public void findByBrand() {
//            try {
                int choice;
                do{
                System.out.println("*******lựa chọn hãng xe:********");
                System.out.println("|          1. Honda            |");
                System.out.println("|          2. Yamaha           |");
                System.out.println("|          3. Suzuki           |");
                System.out.println("|         4. Kawasaki          |");
                System.out.println("|            5. BMW            |");
                System.out.println("|          6. Ducati           |");
                System.out.println("|      7. Haley Davision       |");
                System.out.println("|         8. Triumph           |");
                System.out.println("|          0. thoát            |");
                System.out.println("********************************");
                    choice = validate.checkChoiceInt();
                if (choice >= 0 && choice <=8) {
                    switch (choice) {
                        case 1: findBrand("honda");
                        break;
                        case 2: findBrand("yamaha");
                        break;
                        case 3: findBrand("suzuki");
                        break;
                        case 4: findBrand("kawasaki");
                        break;
                        case 5: findBrand("BMW");
                        break;
                        case 6: findBrand("ducati");
                        break;
                        case 7: findBrand("Haley Davision");
                        break;
                        case 8: findBrand("Triumph");
                        break;
                    }
                } else {
                    System.err.println("********************************************************");
                    System.err.println("bạn vui lòng nhập đúng lựa chọn trong menu");
                    System.err.println("********************************************************");
                }
            }while (choice != 0);
//            }catch (Exception e){
//                System.err.println("********************************************************");
//                System.err.println("bạn vui lòng nhập đúng lựa chọn trong menu");
//                System.err.println("********************************************************");
//            }

    }
    public void findBrand(String brand){
        boolean check = true;
        for (int i = 0; i < motorsList.size(); i++) {
            if (motorsList.get(i).getBrand().equalsIgnoreCase(brand)){
                System.out.println(motorsList.get(i));
                check = false;
            }
        }
        if (!check) {
            purchaseMenu();
        }
        if (check){
            System.err.println("********************************************************");
            System.err.println("chúng tôi không còn chiếc xe nào của hãng này!");
            System.err.println("********************************************************");
        }
    }
    //2 tìm theo tên
    public void findByModel2(){
        try {
            System.out.println("********************************************************");
            System.out.println("nhập tên xe: ");
            System.out.println("hoặc quit để thoát");
            System.out.println("********************************************************");
            String findModel = scanner.nextLine();
            boolean check = true;
            for (int i = 0; i < motorsList.size(); i++) {
                if (findModel.equalsIgnoreCase(motorsList.get(i).getModel())) {
                    System.out.println(motorsList.get(i));
                    check = false;
                }
            }
            if (!check){purchaseMenu();}
            if (findModel.equalsIgnoreCase("quit")) {
                return;
            }
            if (check) {
                System.err.println("********************************************************");
                System.err.println("chúng tôi không có xe này");
                System.err.println("bạn vui lòng chọn xe khác");
                System.err.println("********************************************************");
                findByModel2();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("hệ thống gặp sự cố hoặc do bạn thao tác sai.");
            System.err.println("chúng tôi rất tiếc về sự cố này!");
            System.err.println("bạn có thể thử lại! ");
        }
    }
    //3 tìm theo màu sơn
    public void findProductByColor(){
            int choice = 0;
            do {
                System.out.println("**********chọn màu sơn***********");
                System.out.println("|      1. màu đen (black)       |");
                System.out.println("|     2. màu trắng (white)      |");
                System.out.println("|  3. màu xanh lá cây (green)   |");
                System.out.println("|    4. màu xanh biển (blue)    |");
                System.out.println("|       5. màu vàng (yellow)    |");
                System.out.println("|         6. màu đỏ (red)       |");
                System.out.println("|           0. thoát            |");
                System.out.println("|_______________________________|");
                choice =  validate.checkChoiceInt();
                if (choice >= 0 && choice <= 6)
                {
                    switch (choice){
                        case 1: findColor("black");
                        break;
                        case 2: findColor("white");
                        break;
                        case 3: findColor("green");
                        break;
                        case 4: findColor("blue");
                        break;
                        case 5: findColor("yellow");
                        break;
                        case 6: findColor("red");
                        break;
                    }
                }else {
                    System.err.println("********************************************************");
                    System.err.println("vui lòng nhập đúng lựa chon trong menu!");
                    System.err.println("********************************************************");
                }
            }while (choice != 0);
    }
    public void findColor(String color){
        boolean check = true;
        for (int i = 0; i < motorsList.size(); i++) {
            if (motorsList.get(i).getColor().equalsIgnoreCase(color)) {
                System.out.println(motorsList.get(i));
                check = false;
            }
        }
        if (!check){purchaseMenu();}
        if (check){
            System.err.println("********************************************************");
            System.err.println("xin lỗi quý khách, cửa hàng chúng tôi không còn xe màu này");
            System.err.println("********************************************************");
        }

    }
    //4 tìm theo dung tích xy lanh
    public double inputAndCheckCylinderCapacity(){
        double cylinderCapacity = 0;
        try {
             cylinderCapacity = Double.parseDouble(scanner.nextLine());
             if (cylinderCapacity < 0){
                 System.err.println("********************************************************");
                 System.err.println("dung tích xy lanh phải từ 0 trở lên!");
                 System.err.println("********************************************************");
                 inputAndCheckCylinderCapacity();
             }
             return cylinderCapacity;
        }catch (Exception e){
            System.err.println("********************************************************");
            System.err.println("vui lòng nhập đúng kiểu dữ liệu số thực cho dung tích xy lanh");
            System.err.println("********************************************************");
            return inputAndCheckCylinderCapacity();
        }
    }
    public void finProductByCylinderCapacity(){
        System.out.println("********************************************************");
            System.out.println("dung tích xy lanh từ: ");
            double minCylinderCapacity = inputAndCheckCylinderCapacity();
            System.out.println("đến: ");
            double maxCylinderCapacity = inputAndCheckCylinderCapacity();
        System.out.println("********************************************************");
            boolean check = true;
            if (minCylinderCapacity > maxCylinderCapacity ||minCylinderCapacity < 0){
                System.err.println("********************************************************");
                System.err.println("thao tác lỗi trong quá trình nhập dung tích xy lanh");
                System.err.println("số nhập sau phải lớn hơn số nhập trước và cả 2 phải lớn hơn 0");
                System.err.println("bạn có thể thử lại hoặc nhập 2 lần 0  để thoát");
                System.err.println("********************************************************");
                finProductByCylinderCapacity();
            }
            if (minCylinderCapacity < maxCylinderCapacity && minCylinderCapacity > 0) {
                for (int i = 0; i < motorsList.size(); i++) {
                    if (motorsList.get(i).getCylinderCapacity() >= minCylinderCapacity && motorsList.get(i).getCylinderCapacity() <= maxCylinderCapacity) {
                        System.out.println(motorsList.get(i));
                        check = false;
                    }
                }
                if (!check){purchaseMenu();}
                if (check) {
                    System.err.println("********************************************************");
                    System.err.println("chúng tôi không có phân khúc bạn tìm, \n bạn có thể thử lại hoặc nhập 2 lần 0  để thoát");
                    System.err.println("********************************************************");
                    finProductByCylinderCapacity();
                }
            } else if (minCylinderCapacity ==0 && maxCylinderCapacity ==0) {
                return;
            }else {
                System.err.println("********************************************************");
                    System.err.println("dung tích xy lanh nhập trước  phải nhỏ hơn số nhập sau và cả 2 phải lớn hơn 0");
                    System.err.println("vui lòng kiểm tra lại");
                System.err.println("********************************************************");
                }
    }
    //5 tìm theo tầm giá

    public void findProductByPrice(){
        System.out.println("********************************************************");
            System.out.println("bạn muốn xe có giá từ: ");
            double minPrice = validate.inputAndCheckPrice();
            System.out.println("đến:");
            double maxPrice =  validate.inputAndCheckPrice();
        System.out.println("********************************************************");
            boolean check = true;
            if (minPrice > maxPrice || maxPrice < 0 || minPrice < 0){
                System.err.println("********************************************************");
                System.err.println("thao tác lỗi trong quá trình nhập giá");
                System.err.println("giá nhập trước phải lớn hơn giá nhập sau và cả 2 đều lơn hơn 0");
                System.err.println("bạn có thể thử lại hoặc nhập 2 lần 0  để thoát");
                System.err.println("********************************************************");
                findProductByPrice();
            }
            if (minPrice >= 0 && maxPrice > minPrice) {
                for (int i = 0; i < motorsList.size(); i++) {
                    if (motorsList.get(i).getPrice() >= minPrice && motorsList.get(i).getPrice() <= maxPrice){
                        System.out.println(motorsList.get(i));
                        check = false;
                    }
                }
                if (!check){purchaseMenu();}
                if (check) {
                    System.err.println("********************************************************");
                    System.err.println("xin lỗi! chúng tôi không còn xe trong tâm giá này!\nbạn có thể thử lại hoặc nhập 2 lần 0 để thoát");
                    System.err.println("********************************************************");
                    findProductByPrice();
                }
            }else if (maxPrice == 0 && minPrice ==0){
                return;
            }
    }
    //6 tìm theo kiểu dáng xe
    public void findProductByCategory() {
            int choice;
                    do {
                        System.out.println("|----------chọn kiểu dáng xe----------|");
                        System.out.println("|            1. underbone             |");
                        System.out.println("|             2. scooter              |");
                        System.out.println("|            3. sport bike            |");
                        System.out.println("|            4. naked bike            |");
                        System.out.println("|              5. cruiser             |");
                        System.out.println("|             6. adventure            |");
                        System.out.println("|              7. tracker             |");
                        System.out.println("|             8. motocross            |");
                        System.out.println("|              0. thoát               |");
                        System.out.println("***************************************");
                    choice = validate.checkChoiceInt();
                    if (choice >= 0 && choice <= 8) {
                        switch (choice) {
                            case 1:
                                findByCategory("underbone");
                                break;
                            case 2:
                                findByCategory("scooter");
                                break;
                            case 3:
                                findByCategory("sport bike");
                                break;
                            case 4:
                                findByCategory("naked bike");
                                break;
                            case 5:
                                findByCategory("cruiser");
                                break;
                            case 6:
                                findByCategory("adventure");
                                break;
                            case 7:
                                findByCategory("tracker");
                                break;
                            case 8:
                                findByCategory("motocross");
                                break;
                        }
                    }else{
                        System.out.println("********************************************************");
                        System.out.println("bạn vui lòng nhập đúng lựa chọn trong menu");
                        System.out.println("********************************************************");
                    }
                    }while (choice != 0);
    }
    public void findByCategory(String category){
        boolean check = true;
        for (int i = 0; i < motorsList.size(); i++) {
            if (motorsList.get(i).getCategory().equalsIgnoreCase(category)){
                System.out.println(motorsList.get(i));
                check = false;
            }
        }
        if (!check){purchaseMenu();}
        if (check){
            System.out.println("********************************************************");
            System.err.println("xin lỗi, của hàng chúng tôi không còn kiểu xe này!");
            System.out.println("********************************************************");
        }
    }
//    //____________________________________________
//    public int checkQuantity(){
//        int quantity = 0;
//        try {
//        System.out.println("**************************************");
//        System.out.println("bạn muốn mua mấy chiếc? ");
//            quantity = Integer.parseInt(scanner.nextLine());
//            System.out.println("********************************************************");
//            if (quantity <= 0){
//                System.err.println("********************************************************");
//                System.err.println("bạn vui lòng nhập đúng số nguyên dương");
//                System.err.println("********************************************************");
//                checkQuantity();
//            }
//            return quantity;
//        }catch (Exception e){
//            System.err.println("********************************************************");
//            System.err.println("bạn vui lòng nhâp đúng số nguyên dương");
//            System.err.println("********************************************************");
//            checkQuantity();
//        }
//        return quantity;
//    }


    public void purchase(){
//        try {
            int indexProduct = adminActivities.findProductIndexById();
            if (indexProduct == -2){return;}
            if (indexProduct != -1) {
                int quantity = validate.quantity();
                while (motorsList.get(indexProduct).getInventory() < quantity) {
                    System.err.println("chúng tôi không đủ xe so với số lượng đặt hàng");
                    System.err.println("hiện tại, chúng tôi chỉ còn " + motorsList.get(indexProduct).getInventory() + " chiếc xe loại này");
                    System.err.println("bạn có thể chọn lại sản phẩm hoặc nhập 0 để thoát");
                    quantity = validate.quantity();
                    if (quantity == 0) {
                        return;
                    }
                }
                PurchasedProducts purchasedProducts = new PurchasedProducts(motorsList.get(indexProduct).getModel(), motorsList.get(indexProduct).getColor(), quantity);
                List<PurchasedProducts> newPurchasedProductsList = new ArrayList<>();
                newPurchasedProductsList.add(purchasedProducts);
                System.out.println("********************************************************");
                System.out.println(" quý khách cho biết số điện thoại");
                String phoneNumber = validate.inputAndCheckPhoneNumber();
                int checkCustomersDuplication;
                do {
                    checkCustomersDuplication = validate.checkCustomersDuplication(phoneNumber);
                    if (checkCustomersDuplication == 1) {
                        for (int i = 0; i < customerList.size(); i++) {
                            if (customerList.get(i).getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                                customerList.get(i).getPurchasedProducts().add(purchasedProducts);
                                try {
                                    customersFileController.writeCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt", customerList);
                                    System.out.println("   ❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤ cảm ơn quý khách đã mua hàng! ❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤");
                                    return;
                                } catch (Exception e) {
                                    System.out.println("lỗi ghi file, \n thao tác mua chưa lưu vào hệ thốn!");
                                }
                                try {
                                    minusProductQuantity(indexProduct, quantity);
                                } catch (Exception e) {
                                    System.err.println("số lượng hàng trong kho chưa bị trừ sau khi mua");
                                }
                            }
                        }
                    } else if (checkCustomersDuplication == 2) {
                        System.out.println("số điện thoại trùng với khách hàng khác! vui lòng thử lại");
                        System.out.println("mời quý khách nhập lại số điện thoại! ");
                        System.out.println("bạn cũng có thể nhập 10 số 0 để thoát!");
                        phoneNumber = validate.inputAndCheckPhoneNumber();
                    }
                } while (checkCustomersDuplication >= 1 && checkCustomersDuplication < 3);
                if (phoneNumber.equals("0000000000")){return;}
                if (checkCustomersDuplication == 3) {
                    System.out.println("**************************************");
                    System.out.println(" họ và tên của quý khách: ");
                    String name = scanner.nextLine();
                    System.out.println("**************************************");
                    System.out.println("tuổi: ");
                    int age = validate.inputAndCheckAge();
                    System.out.println("**************************************");
                    System.out.println("nhập địa chỉ: ");
                    String address = scanner.nextLine();
                    System.out.println("********************************************************");
                    Customer newCustomer = new Customer(name, age, address, phoneNumber, newPurchasedProductsList);
                    try {
                        minusProductQuantity(indexProduct, quantity);
                    } catch (Exception e) {
                        System.err.println("thao tác gặp sự cố! \n số lượng sản phẩm trong kho chưa bị trừ");
                    }
                    customerList.add(newCustomer);
                    try {
                        customersFileController.writeCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt", customerList);
                        System.out.println("   ❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤ cảm ơn quý khách đã mua hàng! ❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤");
                        return;
                    } catch (Exception e) {
                        System.err.println("lỗi ghi file");
                        System.err.println("các thao tác trên chưa được thực hiện");
                    }
                }
            }
            }
//            else {
//            System.err.println("không tìm thấy sản phẩm có id này");
//        }
//        }catch (Exception e){
//            System.err.println("************************************************************************");
//            System.err.println("hệ thống gặp lỗi hoặc do bạn thao tác sai trong quá trình mua hàng và tạo khách hàng");
//            System.err.println("************************************************************************");
//        }
//    public boolean checkStockProducts(int id){
//        if (motorsList.get(id).getInventory() > 0){
//            return true;
//        }
//        return false;
//    }

    public void minusProductQuantity(int indexProduct, int quantity) throws Exception {
        if (motorsList.size() >0){
        for (int i = 0; i < motorsList.size(); i++) {
            if (i == indexProduct){
                motorsList.get(i).setInventory(motorsList.get(i).getInventory() - quantity);
                productFileController.writeMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt", motorsList);
            }
        }
        }else {
            System.err.println("********************************************************");
            System.err.println("danh sách motor trống! check lại hệ thống");
            System.err.println("********************************************************");
        }
    }
    public void purchaseMenu(){
        int choice;
                System.out.println("**********bạn muốn mua hàng?************");
                System.out.println("|             1. mua hàng               |");
                System.out.println("|              2. thoát                 |");
                System.out.println("|***************************************|");
                choice = validate.checkChoiceInt();
                if (choice == 1) {
                    purchase();
                }else return;
    }
}
