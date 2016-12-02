package models;

/**
 * Created by theapache64 on 2/12/16.
 */
public class Request {
    private final String wordId, IPAddress;

    public Request(String wordId, String IPAddress) {
        this.wordId = wordId;
        this.IPAddress = IPAddress;
    }

    public String getWordId() {
        return wordId;
    }

    public String getIPAddress() {
        return IPAddress;
    }
}
