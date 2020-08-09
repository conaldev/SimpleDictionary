package service;

import java.io.IOException;

public interface IControl {
    String searchMeaning(String keySearch);

    String searchPronunciation(String keySearch);

    String searchWordClass(String keySearch);

    void addNewWorld(String newWord,String newProunce, String newType, String newMeaning);

    void editWord(String word);

    void deleteWord(String word);
}
