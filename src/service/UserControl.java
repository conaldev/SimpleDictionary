package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserControl implements IControl {
    private HashMap<String, String> dicHashMap;
    private final ArrayList<String> wordSearchedList = new ArrayList<>();
    private static volatile UserControl instance;

    public void setDicHashMap(HashMap<String, String> dicHashMap) {
        this.dicHashMap = dicHashMap;
    }

    public static UserControl getInstance() {
        if (instance == null) {
            instance = new UserControl();
        }
        return instance;
    }

    @Override
    public String searchMeaning(String keySearch) {
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        Matcher matcher = pattern.matcher(this.dicHashMap.get(keySearch));
        String text = "";
        if (matcher.find()) {
            text += matcher.group(2).replaceAll("_", "\n");
        }
        return text;
    }

    @Override
    public String searchPronunciation(String keySearch) {
        String regexPronounce = "^/(.*?)_";
        Pattern pattern = Pattern.compile(regexPronounce);
        Matcher matcher = pattern.matcher(this.dicHashMap.get(keySearch));
        String text = "";
        if (matcher.find()) {
            text += matcher.group(1).replaceAll(" ", "\n");
        }
        return text;
    }

    @Override
    public String searchWordClass(String keySearch) {
        String regexTypeOfWord = "\\*(.*?_)";
        Pattern patternTypeOfWord = Pattern.compile(regexTypeOfWord);
        Matcher matcherTypeOfWord = patternTypeOfWord.matcher(this.dicHashMap.get(keySearch));
        String text = "";
        while (matcherTypeOfWord.find()) {
            text += matcherTypeOfWord.group(1).replaceAll("_", "\n");
        }
        return text;
    }

    @Override
    public void addNewWorld(String newWord, String newPronounce, String newTypeOfWord, String newMeaning) {
        String contentNewWord = newWord + " " + newPronounce + "\n" + "*" + newTypeOfWord + "\n" + "-" + newMeaning + "\n\n" + "@";
        writeToDictionay(contentNewWord);
    }

    @Override
    public void editWord(String word) {

    }

    @Override
    public void deleteWord(String word) {

    }

    public boolean isWordExist(String keySearch) {
        return this.dicHashMap.containsKey(keySearch);
    }
    public void writeToDictionay(String content){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            bufferedWriter.write(content);
           bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
