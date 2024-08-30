/*Escribe un programa en Java que permita a un cliente seleccionar el paquete turístico que desea reservar
en "El viajero feliz" y la cantidad de personas en su grupo. El programa debe calcular el precio total a pagar
teniendo en cuenta las condiciones de descuento y bonificación, y mostrar el precio total.
Además, el programa debe determinar si el cliente es elegible para bonificaciones y descuentos, y en caso
afirmativo, mostrar el monto de las bonificaciones y descuentos aplicados.*/
import javax.swing.JOptionPane;

public class ViajeroFeliz{
	public static void main(String arg[]){
		String eleccion;
		int paquete, miembros, anticipo, dias;
		double precioInicial, precioSub, precioFinal, descuentoAn, descuentoMi, descuentoAmb;
		dias=0;
		precioInicial=0;
		precioSub=0;
		precioFinal=0;
		descuentoAn=0;
		descuentoMi=0;
		do{
		eleccion=JOptionPane.showInputDialog(null,"Por favor seleccione un paquete turistico para su viaje \n"+
		"1. Paquete Estándar \n"+
		"2. Paquete Premium \n"+
		"3. Paquete VIP");
		paquete=Integer.parseInt(eleccion);
		}while(paquete!=1 && paquete!=2 && paquete!=3);
	
		eleccion=JOptionPane.showInputDialog(null,"Por favor ingrese la cantidad de personas del grupo");
		miembros=Integer.parseInt(eleccion);
		
		eleccion=JOptionPane.showInputDialog(null,"Por favor ingrese la cantidad de dias que desea viajar");
		dias=Integer.parseInt(eleccion);
		
		eleccion=JOptionPane.showInputDialog(null,"Por favor ingrese la cantidad de meses de anticipación con los que desea reservar");
		anticipo=Integer.parseInt(eleccion);
		
			switch(paquete){
			case 1:
				precioInicial=1000;
				precioSub=precioInicial*miembros;
				precioSub=precioSub*dias;
			break;
			case 2:
				precioInicial=1500;
				precioSub=precioInicial*miembros;
				precioSub=precioSub*dias;
			break;
			case 3:
				precioInicial=2500;
				precioSub=precioInicial*miembros;
				precioSub=precioSub*dias;
			break;
			default:
				JOptionPane.showMessageDialog(null,"Opcion invalida, intente nuevamente");
			break;
		}//Fin switch
	
		if(miembros>=4 && anticipo>=6){
			descuentoAn=precioSub*0.05;
			descuentoMi=precioSub*0.1;
			descuentoAmb=descuentoAn+descuentoMi;
			precioFinal=precioSub-descuentoAmb;
			}else{if(anticipo>=6){
				descuentoAn=precioSub*0.05;
				precioFinal=precioSub-descuentoAn;
				}else{if(miembros>=4){
					descuentoMi=precioSub*0.1;
					precioFinal=precioSub-descuentoMi;
					}}
				}//Fin if
		if(paquete==3){
			dias++;
			}
		JOptionPane.showMessageDialog(null,"\n"+
		"********************************************************\n"+
		"Subtotal:               "+precioSub+"\n"+
		"Descuento por miembros: "+descuentoMi+"\n"+
		"Descuento por anticipo: "+descuentoAn+"\n"+
		"Costo Final:            "+precioFinal+"\n"+
		"Dias de hospedaje:      "+dias+"\n"+
		"********************************************************");

	}//Fin main
}//Fin Clase
