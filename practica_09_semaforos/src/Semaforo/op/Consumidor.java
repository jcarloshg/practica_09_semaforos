package Semaforo.op;


public class Consumidor implements Runnable {
	
	BufferLimitado b = null;
	
	public Consumidor (BufferLimitado initb) {
		b = initb;
		new Thread( this ).start();
	}

	public void run() {
		double item;
		while( true ) {
			item = b.fetch();
//			System.out.println( "Estraido: " + item );
			Util.mySleep(3000);
		}
	}
	
}
