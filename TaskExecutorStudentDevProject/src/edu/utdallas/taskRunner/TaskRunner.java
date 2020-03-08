package edu.utdallas.taskRunner;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.blockingFIFO.BlockingFIFO;


public class TaskRunner implements Runnable {
	
	public void run() {
		while(true) {
			Task task = fifo.take();
			try {
				task.execute();
			}
			catch(Throwable th) {
				System.out.print("The following task failed to execute: ");
				System.out.print(task.getName());
				System.out.println();
			}
		}
	}
}
