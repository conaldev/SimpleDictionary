package service;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DicionaryHashMap {
    private static volatile DicionaryHashMap instance;
    public static DicionaryHashMap getInstance(){
        if(instance ==null)
            instance = new DicionaryHashMap();
        return instance;
    }
    public HashMap<String, String> readToHashMapDic(String srcDicFile) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(new File(srcDicFile)), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter("\\Z");
        String stringDic = scanner.next();
        scanner.close();
        stringDic = stringDic.replaceAll("\\n", "_");
        stringDic = stringDic.replaceAll("(\\s/)", "///");
        String regexKeyValue = "@(.*?)/(.*?)_{2}";
        HashMap<String, String> dictionaryMap = new HashMap<>();
        Pattern pattern = Pattern.compile(regexKeyValue);
        Matcher matcher = pattern.matcher(stringDic);
        while (matcher.find()) {
            dictionaryMap.put(matcher.group(1), matcher.group(2));
        }
        return dictionaryMap;
    }
}
