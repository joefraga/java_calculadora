import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Calculator {

	public static void main(String[] args) {
		VentanaCalculadora miventana=new VentanaCalculadora();
		miventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miventana.setVisible(true);

	}

}

class VentanaCalculadora extends JFrame{
	VentanaCalculadora(){
		setTitle("Calculadora");
		//setBounds(200,200,450,300); se quita al usar el metodo pack()
		LaminaCalculadora lamina=new LaminaCalculadora();
		add(lamina);
		pack(); //**tama�o por defecto
	}
}

class LaminaCalculadora extends JPanel{
	private JPanel lamina2; //declaro fuera del constructor la 2a lamina
	private JButton pantalla;
	private boolean principio;
	private double resultado;
	private String ultimaOperacion;
	public LaminaCalculadora(){
		principio=true;
		setLayout(new BorderLayout());
		pantalla=new JButton("0");
		pantalla.setEnabled(false);
		add(pantalla, BorderLayout.NORTH);
		
		
		//construyo el grid
		lamina2=new JPanel();
		lamina2.setLayout(new GridLayout(4,4));
		
		ActionListener insertar=new InsertaNumero();
		ActionListener orden=new AccionOrden();
		
		ponerBoton("7",insertar);
		ponerBoton("8",insertar);
		ponerBoton("9",insertar);
		ponerBoton("/",insertar);
		ponerBoton("4",insertar);
		ponerBoton("5",insertar);
		ponerBoton("6",insertar);
		ponerBoton("*",orden);
		ponerBoton("1",insertar);
		ponerBoton("2",insertar);
		ponerBoton("3",insertar);
		ponerBoton("-", orden);
		ponerBoton("0",insertar);
		ponerBoton(".",insertar);
		ponerBoton("=",orden);
		ponerBoton("+",orden);
				
		add(lamina2, BorderLayout.CENTER);
		ultimaOperacion="=";
	}
	
	private void ponerBoton(String rotulo, ActionListener oyente) {
		
		JButton boton=new JButton(rotulo);
		
		boton.addActionListener(oyente); //pongo a la escucha el boton
		
		lamina2.add(boton);
	}
	
	private class InsertaNumero implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String entrada=e.getActionCommand();
			if(principio) {
				pantalla.setText("");
				principio=false;
			}
			pantalla.setText(pantalla.getText() + entrada);
			
		}
		
	}
	
	private class AccionOrden implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String operacion = e.getActionCommand();
			calcular(Double.parseDouble(pantalla.getText()));
			ultimaOperacion=operacion;
			principio=true;
		}
		
		public void calcular(double x) {
			if(ultimaOperacion.equals("+")) {
				resultado+=x;
			}
			else if(ultimaOperacion.equals("-")) {
				resultado-=x;
			}
			else if(ultimaOperacion.equals("*")) {
				resultado*=x;
			}else if(ultimaOperacion.equals("/")) {
				resultado/=x;
			}
			else if(ultimaOperacion.equals("=")) {
				resultado=x;
			}
			pantalla.setText("" + resultado);
		}
		
	}
}