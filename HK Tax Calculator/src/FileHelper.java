import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
    public void readTax(String str, String name){
        File f = new File("src/file/" + name + "'s receipt.csv");
        if (f.exists()){
            f = new File("src/file/" + name + "1's receipt.csv");
        }
        FileOutputStream outputStream = null;
        try{
            try {
                outputStream = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            byte[] b = str.getBytes();
            try {
                outputStream.write(b);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }finally{
            try{
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
