package com.bowleu.exam.java;

import java.util.LinkedList;
import java.util.Queue;

public class WorkerThread extends Thread {

    private final Queue<Runnable> taskQueue = new LinkedList<>();
    private boolean running = true;

    public void addTask(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void shutdown() {
        running = false;
        synchronized (taskQueue) {
            taskQueue.notify();
        }
    }

    @Override
    public void run() {
        while (running) {
            Runnable task;

            synchronized (taskQueue) {
                while (taskQueue.isEmpty() && running) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (!running) {
                    break;
                }

                task = taskQueue.poll();
            }
            if (task != null) {
                task.run();
            }
        }
    }
}
