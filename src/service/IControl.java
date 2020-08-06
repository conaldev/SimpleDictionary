package service;

import java.io.IOException;

public interface IControl {
    void searchMeaning(String keySearch);
    void searchPronunciation(String keySearch);
    void searchWordClass(String keySearch);
    void viewWordSearched();
    void exportListWord() throws IOException;
    void searchSynonymWord(String keySearch);
}
