import controller.AdminActivities;
import controller.CustomersAtivites;
import validate.Validate;

public class mainTest {
    static CustomersAtivites customersAtivites2;

    static {
        try {
            customersAtivites2 = new CustomersAtivites();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public mainTest() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        CustomersAtivites customersAtivites = new CustomersAtivites();
        AdminActivities adminActivities = new AdminActivities();
        Validate validate = new Validate();
//        adminActivities.addCustomer();
//        adminActivities.createMotors();
//      adminActivities.addCustomer();

        adminActivities.menuEditPurchasedProducts(4);

    }
}
