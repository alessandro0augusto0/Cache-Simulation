package trabalhopratico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<String> stringReader(String path) {
        ArrayList<String> text = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = buffRead.readLine()) != null) {
                text.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
