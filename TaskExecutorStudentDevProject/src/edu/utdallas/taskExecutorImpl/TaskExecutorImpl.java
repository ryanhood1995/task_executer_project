package edu.utdallas.taskExecutorImpl;

import java.util.List;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor
{

	TaskExecutorImpl(int num){
		List runnnerPool = new List<TaskRunner>(num);
		BlockingQueue fifo = new BlockingQueue(num*10);
	}

	@Override
	public void addTask(Task task)
	{
		// TODO Complete the implementation
		fifo.put(task);
	}

}
