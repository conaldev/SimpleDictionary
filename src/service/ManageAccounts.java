package service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageAccounts {
    private static volatile ManageAccounts instance;
    public static HashMap<String, String> hashMapAccounts = new HashMap<>();

    public static ManageAccounts getInstance() {
        if (hashMapAccounts.isEmpty()) {
            try {
                File file = new File("accounts.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    String passWord = reader.readLine();
                    hashMapAccounts.put(line, passWord);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (instance == null)
            instance = new ManageAccounts();
        return instance;
    }

    public void addAccount(String email, String password) {
        hashMapAccounts.put(email, password);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
            String text = "\n" + email + "\n" + password;
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAccount(String email, String password) {
        if (!hashMapAccounts.containsKey(email))
            return false;
        else
            hashMapAccounts.remove(email, password);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.txt"));
            oos.writeObject(hashMapAccounts);
            oos.flush();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean accountAlreadyExist(String email) {
        return hashMapAccounts.containsKey(email);
    }

    public boolean submitLogIn(String email, String password) {
        if (hashMapAccounts.containsKey(email)) {
            return hashMapAccounts.get(email).equals(password);
        }
        return false;
    }
}
