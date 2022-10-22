import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Brad Kivell (20115449)
 * @author Shelvin Kumar
 */
public class DataTracker {

    //------------------------------------[VARIABLES]------------------------------------
    private Path filePath = Paths.get("./resources/DataTracker.txt");
    private String userName = "";
    private int balance = 0;

    HashMap<String, Integer> scoreMap = new HashMap<>();
    boolean pastUser = false;

    FileReader reader;
    BufferedReader buffReader;

    //------------------------------------[CONSTRUCTOR]------------------------------------
    public DataTracker(String userName) {
        try {
            this.reader = new FileReader(filePath.toString());
        } catch (FileNotFoundException ex) {
            try {
                Files.createFile(filePath);
                System.out.println("File Created at " + filePath.toString());
                this.reader = new FileReader(filePath.toString());
            } catch (IOException ex1) {
                System.out.println("Could not create file at " + filePath.toString());
            }
        }
        this.buffReader = new BufferedReader(reader);
        this.userName = userName.trim();
    }

    //------------------------------------[LOAD DATA FROM FILE]------------------------------------
    public void loadSequence() {
        // Load from file
        String line;
        String name;
        Integer scoreRead;
        try {
            while ((line = buffReader.readLine()) != null) {
                // Split line into score & name
                String[] splitStr = line.split("\\s+");
                if (splitStr.length >= 1) {
                    name = splitStr[0];
                    scoreRead = Integer.parseInt(splitStr[1]);
                    if (getUserName().trim().equalsIgnoreCase(name.trim())) {
                        setBalance((int) scoreRead);
                        pastUser = true;
                    }
                    else
                    {
                        setBalance(1000); // Default starting money
                    }
                    scoreMap.put(name, scoreRead);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: IOException while loading file");
        }
    }

    //------------------------------------[SAVE TO FILE]------------------------------------
    public void saveSequence() throws FileNotFoundException {
        try (
                 PrintWriter writer = new PrintWriter(filePath.toString())
                ) {
            if (!pastUser) {
                writer.print(getUserName() + " " + getBalance() + "\n");
            } else {
                scoreMap.put(getUserName(), getBalance());
            }
            for (Map.Entry keyValuePair : scoreMap.entrySet()) {
                writer.print(keyValuePair.getKey() + " " + keyValuePair.getValue() + "\n");
            }
            writer.close();
        }
    }

    // SCORE UPDATE
    public void scoreUpdate(int change) {
        this.setBalance(this.getBalance() + change);
        System.out.println("Current Balance: " + this.getBalance());
    }

    //------------------------------------[GET & SET]------------------------------------
    // RETURNS userName AS String
    public String getUserName() {
        return userName;
    }

    // SETS userName TO PARAM
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // RETURNS balance AS int
    public int getBalance() {
        return balance;
    }

    // SETS balance TO PARAM
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
