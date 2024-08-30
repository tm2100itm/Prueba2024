
import javax.swing.JOptionPane;

public class Barrio
{
	String dato1, dato2, todaInfo;
	double dato3, dato4;
	
	//INSTANCIA de vecino 1 y vecino 2
	Persona vecino1=new Persona ();
	
	//REFERENCIA de vecino 3
	Persona vecino2;
	Persona vecino3;
	
	
	//contructores
	public Barrio()
	{
		dato1="";
		dato2="";
		dato3=0;
		dato4=0;
		
		todaInfo="";
		
		//crear una instancia a partir de una referencia
		vecino2=new Persona ();
		
		//llamo en el momento que se contruye el objeto tipo Barrio, el metodo
		//que se encarga de solicitar los datos de cada vecino
		llenarBarrio();
	}
	
	//metodos de ACCION
	
	public void llenarBarrio()
	{
		for(int contador=1; contador<=3; contador++)
		{
			dato1=JOptionPane.showInputDialog("Digite el Nombre del vecino "+contador);
			dato2=JOptionPane.showInputDialog("Digite el Num. de Identificacion del vecino "+contador);
			
			dato3=Double.parseDouble(JOptionPane.showInputDialog("Digite el Peso del vecino "+contador));
			dato4=Double.parseDouble(JOptionPane.showInputDialog("Digite la Altura del vecino "+contador));
			
			switch(contador)
			{
				case 1:  //trabajamos con el objeto vecino1
					vecino1.setNombre(dato1);
					vecino1.setIdentf(dato2);
					vecino1.setPeso(dato3);
					vecino1.setAltura(dato4);
					
					//concatenar la info del vecino 1
					todaInfo+= "Vecino 1: \n"+vecino1.toString()+"\n\n";
									
				break;
		
				
				case 2:
					vecino2.setNombre(dato1);
					vecino2.setIdentf(dato2);
					vecino2.setPeso(dato3);
					vecino2.setAltura(dato4);
					
					//concatenar la info del vecino 1
					todaInfo+= "Vecino 2: \n"+vecino2.toString()+"\n\n";
				break;
				
				
				case 3:
					vecino3=new Persona (dato1, dato2, dato3, dato4);
					
					//concatenar la info del vecino 1
					todaInfo+= "Vecino 3: \n"+vecino3.toString()+"\n\n";
				break;
			}//fin de switch
		}//fin del for
		
		JOptionPane.showMessageDialog(null, todaInfo);
		
	}//fin de llenarBarrio
	
	
	
}//fin del aclase
