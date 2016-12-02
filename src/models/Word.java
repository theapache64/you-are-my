package models;

/**
 * Created by theapache64 on 2/12/16.
 */
public class Word {

    private final String id, word;

    public Word(String id, String word) {
        this.id = id;
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
}
