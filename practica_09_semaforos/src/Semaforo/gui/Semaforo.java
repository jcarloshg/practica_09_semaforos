package Semaforo.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import Semaforo.op.BufferLimitado;
import Semaforo.op.Consumidor;
import Semaforo.op.Productor;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Semaforo {
	
	// luces de los semaforos
	static GC cLine;
	static GC pLine;
	static Label lightConsumer ,lightProducer;
	static Label lb1, lb2, lb3, lb4, lb5;
	static Label lb6, lb7, lb8, lb9, lb0;
	static Canvas canvas;
	public Display display;

	//shell de la app
	protected Shell shell;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Semaforo window = new Semaforo();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void open() {
		//lo use como atributo de clase.
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static void semConsumerRed(boolean work) {
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				
				if(work) {
					lightConsumer.setBackground(new Color(null, 51, 255, 66));
				}
				else {
					lightConsumer.setBackground(new Color(null, 255, 0, 0));
				}
					
			}
		});
	}
	
	public static void lineaConsumerTrue() {
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				canvas.addPaintListener( (PaintEvent e) -> {
					cLine = e.gc;
					cLine.setBackground(new Color(null, 51, 255, 66));
					cLine.fillRectangle(50, 165, 3, 150);
					cLine.fillRectangle(50, 315, 200, 3);
				});
			}
		});
	}
	
	public static void lineaConsumerFalse() {
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				canvas.addPaintListener( (PaintEvent e) -> {
					cLine = e.gc;
					cLine.setBackground(new Color(null, 100, 100, 100));
					cLine.fillRectangle(50, 165, 3, 150);
					cLine.fillRectangle(50, 315, 200, 3);
				});
			}
		});
	}
	
	public static void lineaPR() {
		
	}
	
	public static void semProducerRed(boolean work) {
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				if(work) {

					lightProducer.setBackground(new Color(null, 51, 255, 66));
					canvas.addPaintListener( (PaintEvent e) -> {
						pLine	= e.gc;
						pLine.setBackground(new Color(null, 51, 255, 66));
						pLine.fillRectangle(50, 165, 3, 150);
						pLine.fillRectangle(50, 315, 200, 3);
					});
				}
				else {

					lightProducer.setBackground(new Color(null, 255, 0, 0));
					canvas.addPaintListener( (PaintEvent e) -> {
						pLine	= e.gc;
						pLine.setBackground(new Color(null, 100, 100, 100));
						pLine.fillRectangle(50, 165, 3, 150);
						pLine.fillRectangle(50, 315, 200, 3);
					});
				}
			}
		});
	}
	
	public static void colorBuffer(int ocupados) {
		System.out.println("ocupados " + ocupados);
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				List<Label> buffer = new ArrayList<Label>();
				buffer.add(lb0);
				buffer.add(lb1);
				buffer.add(lb2);
				buffer.add(lb3);
				buffer.add(lb4);
				buffer.add(lb5);
				buffer.add(lb6);
				buffer.add(lb7);
				buffer.add(lb8);
				buffer.add(lb9);
				
				for (int i = 0; i < ocupados; i++)
					buffer.get(i).setBackground(new Color(null, 255, 0, 224));
				
				int auxNum = 10 - ocupados;
				for (int i = auxNum; i < 10; i++)
					buffer.get(i).setBackground(new Color(null, 251, 255,0));
				
			}
		});
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		
		shell = new Shell();
		shell.setSize(500, 400);
		shell.setText("SWT Application");
		
		canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(10, 10, 464, 341);
		
		canvas.addPaintListener( (PaintEvent e) -> {
			cLine		= e.gc;
			pLine		= e.gc;
			
			//lineas semaforo consumidor
			cLine.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
			cLine.fillRectangle(50, 165, 3, 150);
			cLine.fillRectangle(50, 315, 200, 3);
			
			//lineas semaforo productor
			pLine.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
			pLine.fillRectangle(415, 165, 3, 150);
			pLine.fillRectangle(215, 315, 200, 3);
			
		});
		
		
		Label lblProcesoConsumidor = new Label(canvas, SWT.NONE);
		lblProcesoConsumidor.setBounds(10, 10, 68, 42);
		lblProcesoConsumidor.setText("Proceso Consumidor");
		lblProcesoConsumidor.setAlignment(SWT.CENTER);
		
		Composite composite = new Composite(canvas, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(255, 255, 0));
		composite.setBounds(10, 58, 90, 101);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(25, 34, 55, 15);
		lblNewLabel_2.setText("New Label");
		
		Label lblNewLabel_2_1 = new Label(composite, SWT.NONE);
		lblNewLabel_2_1.setBounds(25, 55, 55, 15);
		lblNewLabel_2_1.setText("New Label");
		
		Label lblProcesoProductor = new Label(canvas, SWT.NONE);
		lblProcesoProductor.setBounds(386, 10, 68, 42);
		lblProcesoProductor.setText("Proceso Productor");
		lblProcesoProductor.setAlignment(SWT.CENTER);
		
		Composite composite_1 = new Composite(canvas, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(255, 0, 255));
		composite_1.setBounds(364, 58, 90, 101);
		
		Label lblNewLabel_2_1_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2_1_1.setBounds(25, 42, 55, 15);
		lblNewLabel_2_1_1.setText("New Label");
		
		Label lblNewLabel_2_1_1_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2_1_1_1.setBounds(25, 63, 55, 15);
		lblNewLabel_2_1_1_1.setText("New Label");
		
		Group grpDatos = new Group(canvas, SWT.NONE);
		grpDatos.setBounds(144, 10, 165, 219);
		grpDatos.setText("Datos");
		
		Label lblNewLabel_2_2 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_2_2.setBounds(10, 21, 55, 15);
		lblNewLabel_2_2.setText("Semaforos");
		
		Label lblNoTrabaja_1 = new Label(grpDatos, SWT.NONE);
		lblNoTrabaja_1.setBounds(36, 50, 55, 15);
		lblNoTrabaja_1.setText("No trabaja");
		
		Label lblNewLabel = new Label(grpDatos, SWT.NONE);
		lblNewLabel.setBounds(10, 50, 20, 20);
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 0, 0));
		
		Label lblNewLabel_1 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_1.setBounds(10, 76, 20, 20);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(0, 255, 0));
		
		Label lblTrabaja_1 = new Label(grpDatos, SWT.NONE);
		lblTrabaja_1.setBounds(36, 76, 55, 15);
		lblTrabaja_1.setText("Trabaja");
		
		Label lblNewLabel_2_1_2 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_2_1_2.setBounds(10, 102, 97, 15);
		lblNewLabel_2_1_2.setText("Celdas del Buffer");
		
		Label lblNewLabel_1_1 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_1_1.setBounds(10, 123, 20, 20);
		lblNewLabel_1_1.setBackground(SWTResourceManager.getColor(255, 255, 0));
		
		Label lblNewLabel_1_2 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_1_2.setBounds(10, 149, 20, 20);
		lblNewLabel_1_2.setBackground(SWTResourceManager.getColor(255, 0, 255));
		
		Label lblNoTrabaja_1_1 = new Label(grpDatos, SWT.NONE);
		lblNoTrabaja_1_1.setBounds(36, 123, 121, 15);
		lblNoTrabaja_1_1.setText("Celda sin elementos");
		
		Label lblTrabaja_1_1 = new Label(grpDatos, SWT.NONE);
		lblTrabaja_1_1.setBounds(36, 149, 121, 15);
		lblTrabaja_1_1.setText("Celda con elementos");
		
		Label lblBufferDeElementos = new Label(canvas, SWT.NONE);
		lblBufferDeElementos.setBounds(57, 288, 124, 23);
		lblBufferDeElementos.setText("Buffer de Elementos");
		lblBufferDeElementos.setAlignment(SWT.CENTER);
		
		lightConsumer = new Label(canvas, SWT.NONE);
		lightConsumer.setBackground(SWTResourceManager.getColor(192, 192, 192));
		lightConsumer.setBounds(106, 58, 30, 30);
		
		lightProducer = new Label(canvas, SWT.NONE);
		lightProducer.setBackground(SWTResourceManager.getColor(192, 192, 192));
		lightProducer.setBounds(328, 58, 30, 30);
		
		Button btnNewButton = new Button(grpDatos, SWT.NONE);
		btnNewButton.setBounds(36, 183, 75, 25);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BufferLimitado buffer = new BufferLimitado();
				Productor productor = new Productor( buffer );
				Consumidor Consumidor = new Consumidor( buffer );
			}
		});
		btnNewButton.setText("Inciciar :)");
		
		
		lb1 = new Label(canvas, SWT.NONE);
		lb1.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb1.setBounds(106, 262, 20, 20);
		
		lb2 = new Label(canvas, SWT.NONE);
		lb2.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb2.setBounds(132, 262, 20, 20);
		
		lb3 = new Label(canvas, SWT.NONE);
		lb3.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb3.setBounds(158, 262, 20, 20);
		
		lb4 = new Label(canvas, SWT.NONE);
		lb4.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb4.setBounds(184, 262, 20, 20);
		
		lb5 = new Label(canvas, SWT.NONE);
		lb5.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb5.setBounds(210, 262, 20, 20);
		
		lb6 = new Label(canvas, SWT.NONE);
		lb6.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb6.setBounds(236, 262, 20, 20);
		
		lb7 = new Label(canvas, SWT.NONE);
		lb7.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb7.setBounds(262, 262, 20, 20);
		
		lb8 = new Label(canvas, SWT.NONE);
		lb8.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb8.setBounds(288, 262, 20, 20);
		
		lb9 = new Label(canvas, SWT.NONE);
		lb9.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb9.setBounds(314, 262, 20, 20);
		
		lb0 = new Label(canvas, SWT.NONE);
		lb0.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lb0.setBounds(340, 262, 20, 20);
	}
}
