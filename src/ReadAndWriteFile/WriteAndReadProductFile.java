package ReadAndWriteFile;

import module.Motors;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteAndReadProductFile {
    public List<Motors> readMotorsFile(String path) throws Exception{
        List<Motors> motorsList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            motorsList = (List<Motors>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return motorsList;
    }
    public void writeMotorsFile(String path, List<Motors> motorsList) throws Exception{
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(motorsList);
            oos.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
