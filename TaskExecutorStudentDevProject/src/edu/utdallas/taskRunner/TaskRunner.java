package edu.utdallas.taskRunner;

public class TaskRunner implements Runnable {

    @Override
    public void run(){
        while(true){
            Task task = fifo.take();
            task.execute();
        }
    }

}