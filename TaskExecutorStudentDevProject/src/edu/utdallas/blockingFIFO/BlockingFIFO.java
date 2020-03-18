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
			System.out.print("HI -- put");
			if (count == 100) {
				notFull.wait();
				continue;
			}
			else {
				break;
			}
		}
		synchronized(this) {
			buffer[nextIn] = task;
			nextIn = (nextIn + 1) % 100;
			count++;
			notEmpty.notify();
		}
	}
	
	public Task take() throws InterruptedException {
		while (true) {
			if (count == 0) {
				System.out.print("HI -- take");
				notEmpty.wait();
				continue;
			}
			else {
				break;
			}
		}
		synchronized(this) {
			Task result = buffer[nextOut];
			nextOut = (nextOut + 1) % 100;
			count--;
			notFull.notify();
			return result;
		}
	}
}
