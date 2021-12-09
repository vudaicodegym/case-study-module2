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

public class AdminActivities {
    final String password = "pvd";
    WriteAndReadCustomersFile customersFileController = new WriteAndReadCustomersFile();
    WriteAndReadProductFile productFileController = new WriteAndReadProductFile();
    Scanner scanner = new Scanner(System.in);
    List<Customer> customerList = customersFileController.readCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt");
    List<Motors> motorsList = productFileController.readMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt");
    Validate validate = new Validate();

    //-------------menu thao tác admin------------
    int count = 0;

    public AdminActivities() throws Exception {
    }

    public void menuAdmin() {
        TotalRevenue totalRevenue = new TotalRevenue();
        try {
            int choice;
            if (login()) {
                do {
                    System.out.println("**********menu admin**********");
                    System.out.println("|       1. khách hàng        |");
                    System.out.println("|    2. quản lý sản phẩm     |");
                    System.out.println("|   3. thống kê doanh thu    |");
                    System.out.println("|         0. thoát           |");
                    System.out.println("|****************************|");
                    choice = validate.checkChoiceInt();
                    if (choice == 0 || choice == 1 || choice == 2 || choice == 3) {
                        switch (choice) {
                            case 1:
                                controlerCustomer();
                                break;
                            case 2:
                                menuControlProducts();
                                break;
                            case 3: totalRevenue.showRevenue();
                        }
                    } else {
                        System.out.println("********************************************************");
                        System.err.println("làm ơn nhập đúng lựa chọn trong menu");
                        System.out.println("********************************************************");
                        menuAdmin();
                    }
                } while (choice != 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("hệ thống gặp sự cố hoặc do bạn thao tác sai.");
            System.err.println("chúng tôi rất tiếc về sự cố này!");
            System.out.println("bạn có thể thử lại! ");
            menuAdmin();
        }
    }

    //-----------đăng nhập-------------
    public boolean login() {
        int count = 0;
        do {
            count++;
            System.out.println("********************************************************");
            System.out.println("nhập password: ");
            System.out.println("hoặc quit để thoát");
            String enterPassword = scanner.nextLine();
            System.out.println("********************************************************");
            if (enterPassword.equalsIgnoreCase(password)) {
                return true;
            } else if (enterPassword.equalsIgnoreCase("quit")) {
                return false;
            } else if (count == 1) {
                System.err.println("you are wrong");
                System.err.println("you can retry " + (5 - count) + " times");
            } else if (count == 2) {
                System.err.println("are you sure?");
                System.err.println("you can retry " + (5 - count) + " times");
            } else if (count == 3) {
                System.err.println("what do you mean?");
                System.err.println("you can retry " + (5 - count) + " times");
            } else if (count == 4) {
                System.err.println("you are a hacker?");
                System.err.println("you can retry " + (5 - count) + " times");
            } else if (count > 4) {
                System.err.println("f*ck off!!!!");
                return false;
            }
        } while (count <= 4);
        return false;
    }

    public void controlerCustomer() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showCustomers();
            System.out.println("*************thao tác với khách hàng***********");
            System.out.println("|              1. thêm khách hàng             |");
            System.out.println("|           2. sửa thông tin khách hàng       |");
            System.out.println("|           3. xóa thông tin khách hàng       |");
            System.out.println("|                    0. thoát                 |");
            System.out.println("|*********************************************|");
            choice = validate.checkChoiceInt();
            if (choice == 0 || choice == 1 || choice == 2 || choice == 3) {
                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        editCustomers();
                        break;
                    case 3:
                        deleteCustomers();
                        break;
                }
            } else System.err.println("làm ơn nhập đúng lựa chọn -_-");
        } while (choice != 0);
    }

    //--------------thêm khách hàng---------------
    public void addCustomer() throws Exception {
        Customer ctm = createCustomer();
        if (ctm != null) {
            customerList.add(ctm);
            customersFileController.writeCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt", customerList);
        }
    }

    //------------tạo khách hàng------------------
    public Customer createCustomer() throws Exception {
        CustomersAtivites customersAtivites = new CustomersAtivites();
        int indexProduct = findProductIndexById();
        if (indexProduct == -2) {
            return null;
        }
        while (indexProduct == -1) {
            System.out.println("********************************************************");
            System.out.println("không có sản phẩm này! check lại id sản phẩm!");
            System.out.println("********************************************************");
            indexProduct = findProductIndexById();
        }
        System.out.println("********************************************************");
        int purchased = validate.quantity();
        while (motorsList.get(indexProduct).getInventory() < purchased) {
            System.err.println("chúng tôi không đủ xe so với số lượng đặt hàng");
            System.err.println("hiện tại, chúng tôi chỉ còn " + motorsList.get(indexProduct).getInventory() + " chiếc xe loại này");
            System.err.println("bạn có thể chọn lại sản phẩm hoặc nhập 0 để thoát");
            purchased = validate.quantity();
            if (purchased == 0) {
                return null;
            }
        }
        System.out.println("********************************************************");
        System.out.println("nhập số điện thoại khách hàng");
        String customerPhoneNumbers = validate.inputAndCheckPhoneNumber();
        System.out.println("********************************************************");
        int checkCustomersDuplication;
        do {
            checkCustomersDuplication = validate.checkCustomersDuplication(customerPhoneNumbers);
            if (checkCustomersDuplication == 1) {
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getPhoneNumber().equalsIgnoreCase(customerPhoneNumbers)) {
                        PurchasedProducts purchasedProducts = new PurchasedProducts(motorsList.get(indexProduct).getModel(), motorsList.get(indexProduct).getColor(), purchased);
                        customerList.get(i).getPurchasedProducts().add(purchasedProducts);
                        try {
                            customersFileController.writeCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt", customerList);
                            System.out.println("đã thêm sản phẩm cho khách hàng!");
                            System.out.println("sẽ không thêm mới khách hàng! ");
                            return null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.err.println("********************************************************");
                            System.err.println("lỗi ghi file khách hàng");
                            System.err.println("thông tin khách hàng chưa được lưu vào file!");
                            System.err.println("********************************************************");
                            return null;
                        }

                    }
                }
            } else if (checkCustomersDuplication == 2) {
                System.err.println("****************************************");
                System.err.println("trùng số điện thoại với khách hàng khác");
                System.err.println("****************************************");
            }


        } while (checkCustomersDuplication >= 1 && checkCustomersDuplication < 3);
        if (checkCustomersDuplication == 3) {
            System.out.println("********************************************************");
            System.out.println("nhập tên khách hàng: ");
            String name = scanner.nextLine();
            System.out.println("********************************************************");
            System.out.println("tuổi: ");
            int age = validate.inputAndCheckAge();
            System.out.println("********************************************************");
            System.out.println("địa chỉ: ");
            String adr = scanner.nextLine();
            System.out.println("********************************************************");
            PurchasedProducts purchasedProducts = new PurchasedProducts(motorsList.get(indexProduct).getModel(), motorsList.get(indexProduct).getColor(), purchased);
            List<PurchasedProducts> newPurchasedProducts = new ArrayList<>();
            newPurchasedProducts.add(purchasedProducts);
            Customer newCustomers = new Customer(name, age, adr, customerPhoneNumbers, newPurchasedProducts);
            return newCustomers;
        }
    return null;
}
//        }catch (Exception e){
////            e.printStackTrace();
//            System.err.println("********************************************************");
//            System.err.println("hệ thống gặp sự cố hoặc do bạn thao tác sai tại mục tạo mới khách hàng");
//            System.err.println("chúng tôi rất tiếc về sự cố này!");
//            System.err.println("bạn có thể thử lại! ");
//            System.err.println("********************************************************");
//            return createCustomer();
//        }

    //-------------hiển thị danh sách khách hàng---------------
    public void showCustomers(){
            if (customerList.size() != 0) {
                for(int i = 0; i < customerList.size(); i++) {
                    System.out.println(customerList.get(i));
                }
            } else {
                System.out.println("********************************************************");
                System.out.println("danh sách khách hàng tạm thời trống");
                System.out.println("********************************************************");
            }
    }
    //-------------sửa thông tin khách hàng------------------
    public void editCustomers() throws Exception {
        try {
            CustomersAtivites customersAtivites = new CustomersAtivites();
//            List<PurchasedProducts> purchasedProductsList = new ArrayList<>();
            int index = findCustomerByPhoneNumber();
            if (index != -1) {
                System.out.println("********************************************************");
                System.out.println(customerList.get(index));
                System.out.println("số điện thoại mới của khách hàng");
                String phoneNumber = validate.inputAndCheckPhoneNumber();
                System.out.println("********************************************************");
                System.out.println("họ và tên khách hàng");
                String name = scanner.nextLine();
                System.out.println("********************************************************");
                System.out.println("tuổi ");
                int age = validate.inputAndCheckAge();
                System.out.println("********************************************************");
                System.out.println("nhập địa chỉ");
                String address = scanner.nextLine();
                System.out.println("********************************************************");
//                int purchaseIndex = findProductIndexById();
//                System.out.println("số lượng đã mua: ");
//                int quantity = Integer.parseInt(scanner.nextLine());
                menuEditPurchasedProducts(index);
                customerList.get(index).setName(name);
                customerList.get(index).setAge(age);
                customerList.get(index).setAddress(address);
                customerList.get(index).setPhoneNumber(phoneNumber);
//                customerList.get(index).setPurchasedProducts(purchasedProductsList);
                customersFileController.writeCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt", customerList);
            }else {
                System.err.println("********************************************************");
                System.err.println("không có khách hàng này!");
                System.err.println("********************************************************");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("********************************************************");
            System.err.println("hệ thống gặp sự cố hoặc do bạn thao tác sai.");
            System.err.println("chúng tôi rất tiếc về sự cố này!");
            System.err.println("bạn có thể thử lại! ");
            System.err.println("********************************************************");
        }
    }
    //-------------tìm index của khách hàng trong danh sách bằng số điện thoại-------------
    public int findCustomerByPhoneNumber(){
            if (customerList.size() > 0) {
                System.out.println("********************************************************");
                System.out.println("nhập số điện thoại khách hàng");
                String findPhoneNumber = validate.inputAndCheckPhoneNumber();
                System.out.println("********************************************************");
                if (findPhoneNumber.equals("0000000000")){return -2;}
                for (int i = 0; i < customerList.size(); i++) {
                    if (findPhoneNumber.equals(customerList.get(i).getPhoneNumber())) {
                        return i;
                    }
                }
            } else {
                System.err.println("danh sách khách hàng trống, vui lòng kiểm tra lại");
            }
        return -1;
    }
    //-------------xóa thông tin khách hàng------------------
    public void deleteCustomers(){
            int index = findCustomerByPhoneNumber();
        while(index == -1){
                System.out.println("********************************************************");
                System.out.println("khách hàng này không có trong danh sách");
                System.out.println("bạn có thể nhập lại số điện thoại");
                System.out.println("hoặc nhập 10 số 0 để hủy thao tác");
                System.out.println("********************************************************");
                index = findCustomerByPhoneNumber();
            }
            if (index == -2){return;}
            if (customerList.size() !=0) {
                if (validate.confirmDelete()) {
                    customerList.remove(index);
                    try {
                        customersFileController.writeCustomersFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\customers.txt", customerList);
                        System.out.println("đã xóa!");
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("********************************************************");
                        System.err.println("lỗi ghi file");
                        System.err.println("danh sách chưa được cập nhật");
                        System.err.println("********************************************************");
                    }
                }
            }else {
                System.err.println("********************************************************");
                System.err.println("danh sách khách hàng trống");
                System.err.println("********************************************************");
            }
    }

    public void menuControlProducts() throws Exception {
        int choice;
            do {
                showMotors();
                System.out.println("|*****thao tác với danh sách sản phẩm*****|");
                System.out.println("|             1. thêm sản phẩm            |");
                System.out.println("|             2. sửa danh mục             |");
                System.out.println("|             3. xóa sản phẩm             |");
                System.out.println("|               0. thoát                  |");
                System.out.println("|*****************************************|");
                    choice = validate.checkChoiceInt();
                    if (choice >= 0 && choice <= 3) {
                        switch (choice) {
                            case 1:
                                addProducts();
                                break;
                            case 2:
                                editListMotors();
                                break;
                            case 3:
                                deleteProduct();
                                break;
                        }
                    } else {
                        System.err.println("********************************************************");
                        System.err.println("vui lòng nhập đúng lựa chon trong menu");
                        System.err.println("********************************************************");
                        menuControlProducts();
                    }
            } while (choice != 0);
    }
    //-----------thêm sản phẩm-----------
    public void addProducts() throws Exception {
            Motors newMotor = createMotors();
            if (newMotor != null) {
                motorsList.add(newMotor);
                productFileController.writeMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt", motorsList);
            }
    }
    //--------------tạo sản phẩm mới-------------
    public Motors createMotors(){
        CustomersAtivites customersAtivites = null;
        try {
            customersAtivites = new CustomersAtivites();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean checkProductDuplicate = true;
            int id;
            if (motorsList.size() == 0) {
                id = 1;
            } else id = motorsList.get((motorsList.size() - 1)).getId() + 1;
            System.out.println("nhập hãng xe");
            String newBrand = scanner.nextLine();
            System.out.println("nhập tên xe");
            String newModel = scanner.nextLine();
            System.out.println("màu sơn");
            String color = scanner.nextLine();
            for (int i = 0; i < motorsList.size(); i++) {
                if (motorsList.get(i).getModel().equalsIgnoreCase(newModel) && motorsList.get(i).getColor().equalsIgnoreCase(color)) {
                    System.out.println("sản phẩm còn trong danh sách, chỉ cần thêm số lượng!");
                    System.out.println("nhập số lượng hàng về: ");
                    int inventory = validate.quantity();
                    motorsList.get(i).setInventory(motorsList.get(i).getInventory() + inventory);
                    checkProductDuplicate = false;
                    try {
                        productFileController.writeMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt", motorsList);
                    } catch (Exception e) {
                        System.err.println("lỗi ghi file. thông tin sản phẩm chưa được cập nhật!");
                    }
                    break;
                }
            }
            if (checkProductDuplicate) {
                System.out.println("dung tíc xy lanh");
                double cylinderCapacity = customersAtivites.inputAndCheckCylinderCapacity();
                System.out.println("dòng xe");
                String category = scanner.nextLine();
                System.out.println("nhập giá");
                double price = validate.inputAndCheckPrice();
                int inventory = validate.quantity();
                Motors motors = new Motors(id, newBrand, newModel, color, cylinderCapacity, category, price, inventory);
                return motors;
            }
        return null;
    }
    //------------tìm sản phẩm theo id--------------
    public int findProductIndexById(){
            if (motorsList.size() != 0) {
                System.out.println("********************************************************");
                System.out.println("nhập id sản phẩm: ");
                System.out.println("hoặc nhập 0 để thoát! ");
                System.out.println("********************************************************");
                int findId = validate.inputAndCheckID();
                if (findId == 0){return  -2;}
                for (int i = 0; i < motorsList.size(); i++) {
                    if (findId == motorsList.get(i).getId()) {
                        return i;
                    }
}
                System.err.println("không tìm thấy sản phẩm có id này trong danh sách \nvui lòng thử lại");
                        return findProductIndexById();
                        } else {
                        System.err.println("********************************************************");
                        System.err.println("danh sách sản phẩm hiện đang rỗng, vui lòng thử lại");
                        System.err.println("********************************************************");
                        }
                        return -1;
    }
    //-----------------sửa sản phẩm ---------------
    public void editListMotors() throws Exception {
        CustomersAtivites customersAtivites = new CustomersAtivites();
        try {
            int index = findProductIndexById();
            if (index == -1 ){
                System.err.println("không có sản phẩm này");
                editListMotors();
            }
            System.out.println("nhập hãng xe");
            String newBrand = scanner.nextLine();
            System.out.println("nhập tên xe");
            String newModel = scanner.nextLine();
            System.out.println("dung tíc xy lanh");
            double cylinderCapacity = customersAtivites.inputAndCheckCylinderCapacity();
            System.out.println("màu sơn");
            String color = scanner.nextLine();
            System.out.println("dòng xe");
            String category = scanner.nextLine();
            System.out.println("nhập giá");
            double price = validate.inputAndCheckPrice();
            int inventory = validate.quantity();
            motorsList.get(index).setBrand(newBrand);
            motorsList.get(index).setModel(newModel);
            motorsList.get(index).setCylinderCapacity(cylinderCapacity);
            motorsList.get(index).setColor(color);
            motorsList.get(index).setCategory(category);
            motorsList.get(index).setPrice(price);
            motorsList.get(index).setInventory(inventory);
            productFileController.writeMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt", motorsList);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("********************************************************");
            System.err.println("hệ thống gặp sự cố hoặc do bạn thao tác sai.");
            System.err.println("chúng tôi rất tiếc về sự cố này!");
            System.out.println("bạn có thể thử lại! ");
            System.err.println("********************************************************");
        }
    }
    //----------xóa sản phẩm-----------
    public void deleteProduct() throws Exception {
        int index = findProductIndexById();
        if (index != -1) {
            motorsList.remove(index);
            productFileController.writeMotorsFile("E:\\CaseStudyModule2\\src\\ReadAndWriteFile\\products.txt", motorsList);
        }else {
            System.err.println("********************************************************");
            System.out.println("không thấy sản phẩm này trong danh sách!");
            System.err.println("********************************************************");
        }
    }
    //------------show list motors---------
    public void showMotors(){
        if (motorsList.size() > 0) {
            System.out.println("đây là danh sách sản phẩm của chúng tôi!");
            for (int i = 0; i < motorsList.size(); i++) {
                System.out.println(motorsList.get(i));
            }
        }else System.out.println("danh sách sản phẩm hiện tại rỗng");
    }


    //--------thao tác với PurchasedProducts---------------
    public void menuEditPurchasedProducts(int index) {
        int choice;
        do {
            showPurchasedProducts(index);
            System.out.println("|*****menu sửa sản phẩm đã mua*****|");
            System.out.println("|               1.thêm             |");
            System.out.println("|               2. sửa             |");
            System.out.println("|               3. xóa             |");
            System.out.println("|**********************************|");
            choice = validate.checkChoiceInt();
            if (choice >= 0 && choice <= 3) {
                switch (choice) {
                    case 1:
                        addPurchasedProducts( index);
                        System.out.println("đã thêm");
                        choice = 0;
                        break;
                    case 2:
                        editPurchasedProduct(index);
                        System.out.println("đã sửa");
                        choice = 0;
                        break;

                    case 3:
                        deletePurchasedProducts(index);
                        System.out.println("đã xóa");
                        choice = 0;
                        break;
                }
            }
        }while (choice != 0);
    }

    //-----thêm------
    public void addPurchasedProducts(int index){
        PurchasedProducts purchasedProducts = createPurchasedProducts();
        if (purchasedProducts != null){
            customerList.get(index).getPurchasedProducts().add(purchasedProducts);
        }
    }
    public PurchasedProducts createPurchasedProducts(){
            PurchasedProducts purchasedProducts;
            System.out.println("tên xe");
            String newName = scanner.nextLine();
            System.out.println("màu");
            String newColor = scanner.nextLine();
            int quantity = validate.quantity();
            purchasedProducts = new PurchasedProducts(newName, newColor, quantity);
            return purchasedProducts;
    }
    //----sửa----
    public void editPurchasedProduct(int index){
        try {
            List<PurchasedProducts> purchasedProducts = customerList.get(index).getPurchasedProducts();
//            CustomersAtivites customersAtivites = new CustomersAtivites();
            int indexPurchasedProduct = findByColorAndModel(index);
            if (indexPurchasedProduct == -2){return;}
            System.out.println("nhập tên xe");
            String newModel = scanner.nextLine();
            System.out.println("nhập màu");
            String newColor = scanner.nextLine();
            int quantity = validate.quantity();
            if (indexPurchasedProduct != 0) {
                purchasedProducts.get(indexPurchasedProduct).setModel(newModel);
                purchasedProducts.get(indexPurchasedProduct).setColor(newColor);
                purchasedProducts.get(indexPurchasedProduct).setQuantity(quantity);
            }else{
                System.err.println("********************************************************");
                System.err.println("khách hàng chưa mua sản phẩm này");
                System.err.println("********************************************************");
            }
        }catch (Exception e){

        }
    }
    //tìm bằng màu và tên
    public int findByColorAndModel(int index){
        try {
            System.out.println("bạn có thể nhập quit 2 lần để dừng thao tác!");
            System.out.println("nhập tên xe");
            String name = scanner.nextLine();
            System.out.println("nhập màu");
            String color = scanner.nextLine();
            List<PurchasedProducts> purchasedProducts = customerList.get(index).getPurchasedProducts();
            if (name.equalsIgnoreCase("quit") || color.equals("quit")){return -2;}
            for (int i = 0; i < purchasedProducts.size(); i++) {
                if (name.equalsIgnoreCase(purchasedProducts.get(i).getModel())) {
                    if (color.equalsIgnoreCase(purchasedProducts.get(i).getColor())) {
                        return i;
                    }
                }
            }
            System.err.println("khách hàng chưa từng mua sản phẩm này");
            return findByColorAndModel(index);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("********************************************************");
            System.err.println("phát sinh lỗi lúc tìm sản phẩm đã mua");
            System.err.println("********************************************************");
            return findByColorAndModel(index);
        }
    }
    //----hiển thị danh mục sản phẩm đã mua----
    public void showPurchasedProducts(int index){
        System.out.println("sản phẩm đã mua: ");
        List<PurchasedProducts> purchasedProducts = customerList.get(index).getPurchasedProducts();
        for (int i = 0; i < purchasedProducts.size(); i++) {
            System.out.println(purchasedProducts.get(i));
        }
    }
    //------deletePurchasedProducts
    public void deletePurchasedProducts(int index){
        int indexPurchasedProduct = findByColorAndModel(index);
        List<PurchasedProducts> purchasedProducts = customerList.get(index).getPurchasedProducts();
        if (indexPurchasedProduct == -1){
            System.err.println("khách hàng chưa từng mua sản phẩm này!");
            System.err.println("kiểm tra lại thao tác");
            deletePurchasedProducts(index);
        }
        if (indexPurchasedProduct == -2){return;}
        else
        if (purchasedProducts.size() != 0){
            if (validate.confirmDelete()) {
                purchasedProducts.remove(indexPurchasedProduct);
            }
        }else System.err.println("khách hàng chưa mua sản phẩm nào!");
    }


}
