package handlers;

import interfaces.RequestHandler;
import logger.Log;

/**
 * Created by fazek on 2016. 07. 07..
 */
public class Test  extends RequestHandler{

    private String test;
    private int a;
    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Log.write(request);
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
