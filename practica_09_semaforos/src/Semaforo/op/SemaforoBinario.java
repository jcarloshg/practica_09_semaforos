package Semaforo.op;

public class SemaforoBinario {

	boolean value;
	public SemaforoBinario( boolean initValue ) {
		value = initValue;
	}

	public synchronized void P() {
		while (value == false )
		Util.myWait(this); // pasa  al cola de procesos bloqueados
		value = false;
	}

	public synchronized void V () {
		value = true;
		notify();
	}

}
