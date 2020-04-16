package Semaforo.op;

import Semaforo.gui.Semaforo;

public class BufferLimitado {
	
	final int size = 10;
	double buffer[] = new double[size];
	int inBuf = 0;
	int outBuf = 0;
	
	SemaforoBinario mutex		= new SemaforoBinario(true);
	SemaforoContador isEmpty	= new SemaforoContador(0);
	SemaforoContador isFull		= new SemaforoContador( size);
	

	public void deposit( double value )
	{
		int num = 0;
		isFull.P(); // espera si el buffer est� lleno
			mutex.P(); // asegura la exclusi�n mutua
				
				buffer[inBuf] = value;
				System.out.println( "Producido: " + buffer[inBuf] );
				inBuf= (inBuf+ 1) % size;
				
				for (int i = 0; i < buffer.length; i++)
					if(buffer[i] > 0)
						num++;
					
				
			mutex.V();
		isEmpty.V(); // notifica a alg�n consumidor en espera
		

		Semaforo.colorBuffer(num);
		Semaforo.semProducerRed(true);
		Semaforo.semConsumerRed(false);
		Semaforo.lineaConsumerFalse();
	}
	
	public double fetch()
	{
		double value;
		int num = 0;
		isEmpty.P(); 
			mutex.P(); 
				value = buffer[outBuf]; 
				System.out.println( "extraido: " + value );
				buffer[outBuf] = 0.0;
				outBuf= (outBuf+1) % size;
				
				for (int i = 0; i < buffer.length; i++)
					if(buffer[i] > 0)
						num++;
					
			mutex.V();
		isFull.V(); // notificaa cualquierproductoren espera

		Semaforo.colorBuffer(num);
		Semaforo.semProducerRed(false);
		Semaforo.semConsumerRed(true);
		Semaforo.lineaConsumerTrue();
		
		return value;
	}
	
}
