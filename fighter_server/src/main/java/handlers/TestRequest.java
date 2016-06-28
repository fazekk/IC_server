package handlers;

import interfaces.RequestHandler;
import logger.Log;

/**
 * Created by zipfs on 2016. 06. 27..
 */
public class TestRequest extends RequestHandler{

    private int a;

    //{"@class" : "handlers.TestRequest", "a" : 10}

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Log.write("UHHU" + a);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
