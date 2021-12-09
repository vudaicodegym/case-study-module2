package ReadAndWriteFile;

import module.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteAndReadCustomersFile {
    public List<Customer> readCustomersFile(String path) throws Exception{
        List<Customer> customersList = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            customersList = (List<Customer>) ois.readObject();
            fis.close();
            ois.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return customersList;
    }
    public void writeCustomersFile (String path, List<Customer> customersList) throws Exception{
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(customersList);
            oos.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
