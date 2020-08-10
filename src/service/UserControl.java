package service;

import javafx.scene.Parent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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

    @Override//add file goc
    public void addNewWorld(String newWord, String newPronounce, String newTypeOfWord, String newMeaning) {
        String contentNewWord = newWord + " /" + newPronounce + "/\n" + "*" + newTypeOfWord + "\n" + "- " + newMeaning + "\n\n" + "@";
        writeToDictionay(contentNewWord,true);
        UserControl.getInstance().setDicHashMap(DicionaryHashMap.getInstance().readToHashMapDic("dictionary.txt"));
    }

    @Override
    public void editWord(String word) {

    }

    @Override//xoa trong dicHashmap
    public void deleteWord(String wordDelete) {
        dicHashMap.remove(wordDelete,dicHashMap.get(wordDelete));
    }

    public boolean isWordExist(String keySearch) {
        return this.dicHashMap.containsKey(keySearch);
    }
    public void writeToDictionay(String content,boolean isAppend){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dictionary.txt",isAppend))) {
            bufferedWriter.write(content);
           bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//thay doi file goc
    public void changePronounce(String keyChange, String newPronounce) {
        newPronounce = "/"+newPronounce+"/";
        String stringDic = creatStringDic();
        String regexPronounce = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexPronounce);
        Matcher matcher = pattern.matcher(this.dicHashMap.get(keyChange));

        if (matcher.matches()) {
            String oldPronounce = matcher.group(1);
            stringDic = stringDic.replaceAll(oldPronounce, newPronounce);
        }
        stringDic = stringDic.replaceAll("_", "\n");
        writeToDictionay(stringDic,false);
    }
    public void changeType(String keyChange,String newType) {
        String stringDic = creatStringDic();
        newType = "*" + newType;
        String regexType = "\\*(.*?_)";
        Pattern pattern = Pattern.compile(regexType);
        Matcher matcher = pattern.matcher(this.dicHashMap.get(keyChange));
        if (matcher.find()) {
            String oldType = matcher.group(1);
            System.out.println(oldType);
            stringDic = stringDic.replaceAll(oldType,newType);
        }
        writeToDictionay(stringDic, false);
    }
    public void changeMeaning(String keyChange, String newMeaning) {
        String stringDic = creatStringDic();
        String regexMeaning = "^-(.*?)_";
        Pattern pattern = Pattern.compile(regexMeaning);
        Matcher matcher = pattern.matcher(this.dicHashMap.get(keyChange));
        if(matcher.matches()) {
            String oldMeaning = matcher.group(1);

            stringDic = stringDic.replace(oldMeaning,"- "+newMeaning);
        }
        stringDic = stringDic.replaceAll("_","\n");
        writeToDictionay(stringDic,false);
    }

    public String creatStringDic() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("dictionary.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        scanner.useDelimiter("\\Z");
        String stringDic = scanner.next();
        scanner.close();
        stringDic = stringDic.replaceAll("\\n", "_");
        return stringDic;
    }
    public void exportToFile(ArrayList<String> arList) throws IOException {
        BufferedWriter bufferedWriter =null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File("exportListSearchedWord.txt"),false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String element : arList){
            bufferedWriter.write(element+"\n");
            bufferedWriter.flush();
        }
    }
}
