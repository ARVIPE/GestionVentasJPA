package modelosBasesDeDatosComunesJPA.ventaDeCoches;

import java.sql.SQLException;

import com.sun.xml.xsom.impl.scd.ParseException;

import modelosBasesDeDatosComunesJPA.GestionCliente;
import modelosBasesDeDatosComunesJPA.GestionCoche;
import modelosBasesDeDatosComunesJPA.GestionConcesionario;
import modelosBasesDeDatosComunesJPA.GestionFabricante;
import modelosBasesDeDatosComunesJPA.GestionVenta;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.controladores.ErrorBBDDException;


public class Principal {

	public static void main(String[] args) throws ParseException, java.text.ParseException, SQLException, ErrorBBDDException {
		menuPrincipal();
	}
	
	/**
	 * @throws java.text.ParseException 
	 * @throws java.text.ParseException 
	 * @throws ParseException 
	 * @throws ErrorBBDDException 
	 * @throws SQLException 
	 * 
	 */
	static void menuPrincipal() throws ParseException, java.text.ParseException, SQLException {

		int opcionElegida;
		do {
			System.out.println("\n\t\t\tGESTI�N DE VENTAS DE COCHES");
			
			System.out.println("\n\t1.- Gesti�n de fabricantes.");
			System.out.println("\t2.- Gesti�n de concesionarios.");
			System.out.println("\t3.- Gesti�n de clientes.");
			System.out.println("\t4.- Gesti�n de coches.");
			System.out.println("\t5.- Gesti�n de ventas.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opci�n: ");
			
			
			opcionElegida = Utils.getIntConsola(0, 5);
			
			switch (opcionElegida) {
			case 0:
				System.out.println("\n!Qu� tenga un feliz d�a!");
				System.exit(0);
			case 1: 
				GestionFabricante.menuGestion();
			case 2:
				GestionConcesionario.menuGestion();
			case 3:
				GestionCliente.menuGestion();
			case 4:
				GestionCoche.menuGestion();
			case 5:
				GestionVenta.menuGestion();
			}
		} while (opcionElegida != 0);
	}

}
