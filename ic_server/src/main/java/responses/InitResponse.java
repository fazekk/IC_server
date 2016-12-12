package responses;

import models.Response;

/**
 * Created by kesze on 2016.12.02..
 */
public class InitResponse extends Response {
    //private boolean success;
    private String type;

    /*public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }*/

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
