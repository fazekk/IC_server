package responses;

import models.Response;

/**
 * Created by Kesze on 2016.08.27..
 */
public class SearchResponse extends Response{
    private boolean success;
    private String message;
    private boolean matchFind;

    public boolean isMatchFind() {
        return matchFind;
    }

    public void setMatchFind(boolean matchFind) {
        this.matchFind = matchFind;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
