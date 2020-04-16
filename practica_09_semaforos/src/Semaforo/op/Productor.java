package Semaforo.op;

import java.util.Random;

public class Productor implements Runnable {

	BufferLimitado b = null;
	
	public Productor( BufferLimitado initb) {
		b = initb;
		new Thread( this ).start();
	}
	
	@Override
	public void run() {
		double item;
		Random r = new Random();
		while( true ) {
			item = r.nextDouble();
			b.deposit( item );
			Util.mySleep(200);
		}
		
	}
	
}
