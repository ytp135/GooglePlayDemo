package com.itheima.googleplaydemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class ExampleUnitTest {

    private static final String TAG = "ExampleUnitTest";
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testObserverPattern() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();

        Teacher teacher = new Teacher();
        teacher.addObserver(student1);
        teacher.addObserver(student2);
        teacher.addObserver(student3);
        teacher.addObserver(student4);
        teacher.publishMessage("放假不解释");


    }

}