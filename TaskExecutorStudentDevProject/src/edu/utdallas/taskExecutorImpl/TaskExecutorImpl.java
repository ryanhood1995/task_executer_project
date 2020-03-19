package edu.utdallas.taskExecutorImpl;

import edu.utdallas.blockingFIFO.BlockingFIFO;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.taskRunner.TaskRunner;

public class TaskExecutorImpl implements TaskExecutor
{
	// Each TaskExecuterImpl has three associated variables or objects:
	// -- A blocking fifo.
	// -- An array of TaskRunners.
	// -- A size (of the array of TaskRunners.
	
	private BlockingFIFO fifo;
	private TaskRunner threadArray[];
	private int arrayLength;
	
	// The below constructor should create the fifo and the threadArray, given the arrayLength.
	public TaskExecutorImpl(int length) {
		this.arrayLength = length;
		this.fifo = new BlockingFIFO();
		this.threadArray = new TaskRunner[arrayLength];
		
		// Now that all items have been initialized, we need to fill the array with task runners.
		for (int i = 0 ; i < arrayLength ; i++) {
			// For each element in our array, we need to create a task runner using the same fifo created here.
			TaskRunner runner = new TaskRunner(this.fifo);
			threadArray[i] = runner;
			
			// And we need to create a thread by passing in our TaskRunner instance and starting it.
			Thread thread = new Thread(runner);
			thread.start();
		}
		
		
	}
	
	// Below is a very simple method that adds a task to the fifo.  If it fails a message is generated.
	@Override
	public void addTask(Task task)
	{
		try {
			fifo.put(task);
		} 
		catch (InterruptedException e) {
			System.out.println("The task failed to be put into the fifo.");
		}
	}

}
