package Semaforo.gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;

public class Main extends Dialog {

	protected Object result;
	protected Shell shell;
	Display display;
	

	Label proGreen, proRed, conRed, conGreen;

	public Main(Shell parent, int style) {
		super(parent, 0);
		setText("SWT Dialog");
	}
	
	public Main(Shell parent) {
		super(parent);
	}
	
	public void cambiarSemaforos(boolean trabajaProductor) {

		if (trabajaProductor) {
			proRed.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
			proGreen.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
		} else {
			proRed.setBackground(display.getSystemColor(SWT.COLOR_RED));
			proGreen.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
		}
	}
	
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	
	
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(500, 400);
		shell.setText(getText());
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(10, 10, 464, 341);
		
		Label lblProcesoConsumidor = new Label(canvas, SWT.NONE);
		lblProcesoConsumidor.setText("Proceso Consumidor");
		lblProcesoConsumidor.setAlignment(SWT.CENTER);
		lblProcesoConsumidor.setBounds(10, 10, 68, 42);
		
		Composite composite = new Composite(canvas, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(255, 255, 0));
		composite.setBounds(10, 58, 90, 101);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("New Label");
		lblNewLabel_2.setBounds(25, 34, 55, 15);
		
		Label lblNewLabel_2_1 = new Label(composite, SWT.NONE);
		lblNewLabel_2_1.setText("New Label");
		lblNewLabel_2_1.setBounds(25, 55, 55, 15);
		
		Label lblProcesoProductor = new Label(canvas, SWT.NONE);
		lblProcesoProductor.setText("Proceso Productor");
		lblProcesoProductor.setAlignment(SWT.CENTER);
		lblProcesoProductor.setBounds(386, 10, 68, 42);
		
		Composite composite_1 = new Composite(canvas, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(255, 0, 255));
		composite_1.setBounds(364, 58, 90, 101);
		
		Label lblNewLabel_2_1_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2_1_1.setText("New Label");
		lblNewLabel_2_1_1.setBounds(25, 42, 55, 15);
		
		Label lblNewLabel_2_1_1_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2_1_1_1.setText("New Label");
		lblNewLabel_2_1_1_1.setBounds(25, 63, 55, 15);
		
		Group grpDatos = new Group(canvas, SWT.NONE);
		grpDatos.setText("Datos");
		grpDatos.setBounds(150, 31, 165, 181);
		
		Label lblNewLabel_2_2 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_2_2.setText("Semaforos");
		lblNewLabel_2_2.setBounds(10, 21, 55, 15);
		
		Label lblNoTrabaja_1 = new Label(grpDatos, SWT.NONE);
		lblNoTrabaja_1.setText("No trabaja");
		lblNoTrabaja_1.setBounds(36, 50, 55, 15);
		
		Label lblNewLabel = new Label(grpDatos, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel.setBounds(10, 50, 20, 20);
		
		Label lblNewLabel_1 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(0, 255, 0));
		lblNewLabel_1.setBounds(10, 76, 20, 20);
		
		Label lblTrabaja_1 = new Label(grpDatos, SWT.NONE);
		lblTrabaja_1.setText("Trabaja");
		lblTrabaja_1.setBounds(36, 76, 55, 15);
		
		Label lblNewLabel_2_1_2 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_2_1_2.setText("Celdas del Buffer");
		lblNewLabel_2_1_2.setBounds(10, 102, 97, 15);
		
		Label lblNewLabel_1_1 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_1_1.setBackground(SWTResourceManager.getColor(255, 255, 0));
		lblNewLabel_1_1.setBounds(10, 123, 20, 20);
		
		Label lblNewLabel_1_2 = new Label(grpDatos, SWT.NONE);
		lblNewLabel_1_2.setBackground(SWTResourceManager.getColor(255, 0, 255));
		lblNewLabel_1_2.setBounds(10, 149, 20, 20);
		
		Label lblNoTrabaja_1_1 = new Label(grpDatos, SWT.NONE);
		lblNoTrabaja_1_1.setText("Celda sin elementos");
		lblNoTrabaja_1_1.setBounds(36, 123, 121, 15);
		
		Label lblTrabaja_1_1 = new Label(grpDatos, SWT.NONE);
		lblTrabaja_1_1.setText("Celda con elementos");
		lblTrabaja_1_1.setBounds(36, 149, 121, 15);
		
		Label lblBufferDeElementos = new Label(canvas, SWT.NONE);
		lblBufferDeElementos.setText("Buffer de Elementos");
		lblBufferDeElementos.setAlignment(SWT.CENTER);
		lblBufferDeElementos.setBounds(57, 268, 124, 23);
		
		conRed = new Label(canvas, SWT.NONE);
		conRed.setBackground(SWTResourceManager.getColor(255, 0, 0));
		conRed.setBounds(106, 58, 20, 20);
		
		conGreen = new Label(canvas, SWT.NONE);
		conGreen.setBackground(SWTResourceManager.getColor(0, 255, 0));
		conGreen.setBounds(106, 82, 20, 20);
		
		proRed = new Label(canvas, SWT.NONE);
		proRed.setBackground(SWTResourceManager.getColor(255, 0, 0));
		proRed.setBounds(338, 58, 20, 20);
		
		proGreen = new Label(canvas, SWT.NONE);
		proGreen.setBackground(SWTResourceManager.getColor(0, 255, 0));
		proGreen.setBounds(338, 82, 20, 20);

	}

}
