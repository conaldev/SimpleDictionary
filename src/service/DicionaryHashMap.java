package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DicionaryHashMap {
    private File srcDicFile;

    public DicionaryHashMap(String srcDic){
        this.srcDicFile = new File(srcDic);
    }
    public HashMap<String,String> readToHashMapDic(){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.srcDicFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(inputStream,"UTF-8");
        scanner.useDelimiter("\\Z");
        String stringDic = scanner.next();
        scanner.close();
        stringDic = stringDic.replaceAll("\\n", "-");
        stringDic = stringDic.replaceAll("(\\s/)","///");
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
