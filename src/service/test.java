package service;

public class test {
    public static void main(String[] args) {
        UserControl.getInstance().setDicHashMap(DicionaryHashMap.getInstance().readToHashMapDic("dictionary.txt"));
        UserControl.getInstance().changeMeaning("vinh","aaa");
    }
}
