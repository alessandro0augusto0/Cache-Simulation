import java.util.ArrayList;

import mappings.Associative;
import mappings.Direct;
import mappings.SetAssociative;
import utils.FileManager;

public class App {
    public static void main(String[] args) {
        String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        ArrayList<String> memoryData = FileManager.stringReader(path + "data/others/memory1.txt");
        ArrayList<String> dataConfig = FileManager.stringReader(path + "data/config/config.txt");
        new SetAssociative(memoryData, dataConfig, "RANDOM");

    }

}