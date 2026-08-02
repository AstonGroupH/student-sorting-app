package org.astongrouph.counter;

import org.astongrouph.collection.CustomArrayList;
import org.astongrouph.model.Student;

public class StudentCounterTask implements Runnable {

    private final CustomArrayList<Student> list;
    private final Student target;
    private final int from;
    private final int to;

    private int result;


    public StudentCounterTask(CustomArrayList<Student> list, Student target, int from, int to) {

        this.list = list;
        this.target = target;
        this.from = from;
        this.to = to;
    }


    @Override
    public void run() {

        for (int i = from; i < to; i++) {

            if (list.get(i).equals(target)) {
                result++;
            }
        }
    }


    public int getResult() {
        return result;
    }


}
