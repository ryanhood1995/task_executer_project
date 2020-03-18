package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public class BlockingFIFO {
	Task buffer[];
	int nextIn;
	int nextOut;
	int count;
	Object notFull;
	Object notEmpty;
	
	public BlockingFIFO() {
		buffer = new Task[100];
		nextIn = 0;
		nextOut = 0;
		count = 0;
		notFull = new Object();
		notEmpty = new Object();	
	}
	
	public void put(Task task) throws InterruptedException {
		while (true) {
			if (count == buffer.length) {
				notFull.wait();
			}
			
			synchronized(this) {
				if (count == buffer.length) {
					continue;
				}
				else {
					buffer[nextIn] = task;
					nextIn = (nextIn + 1) % buffer.length;
					count++;
					notEmpty.notify();
					break;
				}
			}
			
			
		}
	}
	
	public Task take() throws InterruptedException {
		while (true) {
			if (count == 0) {
				notEmpty.wait();
			}
			
			synchronized(this) {
				if (count == 0) {
					continue;
				}
				else {
					Task result = buffer[nextOut];
					nextOut = (nextOut + 1) % buffer.length;
					count--;
					notFull.notify();
					return result;
				}
			}	
		}
	}
}
