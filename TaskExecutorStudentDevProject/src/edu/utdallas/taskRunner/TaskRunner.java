package edu.utdallas.taskRunner;

import java.util.concurrent.ArrayBlockingQueue;
import edu.utdallas.blockingFIFO.BlockingFIFO;
import edu.utdallas.taskExecutor.Task;


public class TaskRunner implements Runnable {
	// Since the threads need access to the fifo (to take tasks), we must include it as an instance variable of class.
	private BlockingFIFO fifo;
	//private ArrayBlockingQueue<Task> fifo;
	
	// The constructor simply initializes the fifo.
	public TaskRunner(BlockingFIFO fifo) {
		this.fifo = fifo;
	}
	
	// The threads will go here to the run() method.  The run method is a simply infinite loop that attempts to 
	// take a task from the fifo, and then execute that task. If this fails, the system will print an error message.
	@Override
	public void run() {
		while(true) {
			try {
				Task task = fifo.take();
				task.execute();
			}
			catch(Exception e) {
				System.out.print("The system either couldn't retrieve a task from the fifo or it couldn't execute the task.");
				System.out.println();
			}
		}
	}
}
