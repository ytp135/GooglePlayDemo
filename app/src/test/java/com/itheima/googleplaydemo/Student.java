package com.itheima.googleplaydemo;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Leon on 2017/1/6.
 */

public class Student implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.print((String)arg + "\n");
    }
}
