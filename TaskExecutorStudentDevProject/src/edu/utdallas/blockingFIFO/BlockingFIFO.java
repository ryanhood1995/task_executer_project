package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public class BlockingFIFO {
	Task[] buffer = new Task[100];
	int nextIn;
	int nextOut;
	int count;
	Object notFull;
	Object notEmpty;
	
	public BlockingFIFO() {
		nextIn = 0;
		nextOut = 0;
		count = 0;
	}
	
	public void put(Task task) throws InterruptedException {
		if (count == buffer.length) {
			notFull.wait();
		}
		
		synchronized(this) {
			buffer[nextIn] = task;
			nextIn = (nextIn + 1) % buffer.length;
			count++;
			notEmpty.notify();
		}
	}
	
	public Task take() throws InterruptedException {
		if (count == 0) {
			notEmpty.wait();
		}
		
		synchronized(this) {
			Task result = buffer[nextOut];
			nextOut = (nextOut + 1) % buffer.length;
			count--;
			notFull.notify();
			return result;
		}
	}
}
