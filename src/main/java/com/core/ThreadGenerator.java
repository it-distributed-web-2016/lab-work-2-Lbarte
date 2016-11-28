package core;

/**
*Generate thread
*
*@author FoxArt
*@version 2.1
*@since 11.15.2016
*/
public class ThreadGenerator implements Runnable {

	private String threadName;
	
	/**
	*Defines new thread instance
	*
	*@param sensorIndex Thread name and his number
	*/
	ThreadGenerator (int sensorIndex) {
		threadName = String.valueOf(sensorIndex);
		Thread threadInstance = new Thread(this, threadName);
		threadInstance.start();
	}
	
	/**
	*Redefine thread commands to execute
	*/
	public void run() {
		Sensor sensorInstance = new Sensor();
		try {
			Thread.sleep(sensorInstance.getDelay());
		} catch (InterruptedException e) {
			Writer write = new Writer();
			write.logData("Thread " + threadName + " interrupted with exception: " + e);
		}
		Writer writerInst = new Writer();
		writerInst.insertDataToDB();
	}
}
