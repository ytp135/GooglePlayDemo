package com.itheima.googleplaydemo;

import java.util.Observable;

/**
 * Created by Leon on 2017/1/6.
 */

public class Teacher extends Observable {


    public void publishMessage(String msg) {
        setChanged();
        notifyObservers(msg);
    }
}
