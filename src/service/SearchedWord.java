package service;

public class SearchedWord {
    private String searchedWord;
    private String pronounce;
    private String type;
    private String meaning;

    public SearchedWord(String searchedWord, String pronounce, String type, String meaning) {
        this.searchedWord = searchedWord;
        this.pronounce = pronounce;
        this.type = type;
        this.meaning = meaning;
    }

    public String getSearchedWord() {
        return searchedWord;
    }

    public void setSearchedWord(String searchedWord) {
        this.searchedWord = searchedWord;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
