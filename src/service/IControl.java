package service;

import java.io.IOException;

public interface IControl {
    String searchMeaning(String keySearch);
    String searchPronunciation(String keySearch);
    String searchWordClass(String keySearch);
    String searchSynonymWord(String keySearch);
}
