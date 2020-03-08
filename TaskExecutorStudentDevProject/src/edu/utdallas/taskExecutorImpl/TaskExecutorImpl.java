package edu.utdallas.taskExecutorImpl;

import java.util.ArrayList;

import edu.utdallas.blockingFIFO.BlockingFIFO;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.taskRunner.TaskRunner;

public class TaskExecutorImpl implements TaskExecutor
{
	BlockingFIFO fifo = new BlockingFIFO();
	
	
	
	@Override
	public void addTask(Task task)
	{
		// TODO Complete the implementation
		try {
			fifo.put(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
