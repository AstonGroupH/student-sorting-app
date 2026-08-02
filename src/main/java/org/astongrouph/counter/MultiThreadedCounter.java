package org.astongrouph.counter;


import org.astongrouph.collection.CustomArrayList;
import org.astongrouph.model.Student;

public class MultiThreadedCounter {

    public static int countOccurrences(CustomArrayList<Student> list, Student target) {

        int threadCount = 4;

        if (list == null || list.isEmpty()) return 0;

        int size = list.size();
        if (threadCount > size) threadCount = size;

        Thread[] threads = new Thread[threadCount];
        int chunk = size / threadCount;

        StudentCounterTask[] tasks = new StudentCounterTask[threadCount];

        for (int i = 0; i < threadCount; i++) {

            int from = i * chunk;
            int to = (i == threadCount - 1) ? size : from + chunk;


            tasks[i] = new StudentCounterTask(list, target, from, to);

            threads[i] = new Thread(tasks[i]);

            threads[i].start();
        }

        int total = 0;

        for (int i = 0; i < threadCount; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            total += tasks[i].getResult();
        }

        return total;
    }
}