package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userControl implements IControl{
    private HashMap<String, String> dicHashMap;
    private ArrayList<String> wordSearchedList = new ArrayList<>();
    @Override
    public String searchMeaning(String keySearch) {

    }

    @Override
    public String searchPronunciation(String keySearch) {

    }

    @Override
    public String searchWordClass(String keySearch) {
        String regexTypeOfWord = "\\*(.*?)_";
        Pattern patternTypeOfWord = Pattern.compile(regexTypeOfWord);
        Matcher matcherTypeOfWord = patternTypeOfWord.matcher(this.dicHashMap.get(keySearch));
        String text = "";
        while (matcherTypeOfWord.find()) {
            text+=matcherTypeOfWord.group(1).replaceAll("_", "\n");
        }
        return text;
    }
    @Override
    public String searchSynonymWord(String keySearch) {

    }
    public boolean isWordExist(String keySearch){
        if(this.dicHashMap.containsKey(keySearch))
            return true;
        return false;
    }
}
