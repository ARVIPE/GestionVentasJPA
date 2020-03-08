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
			System.out.println("\n\t\t\tGESTIÓN DE VENTAS DE COCHES");
			
			System.out.println("\n\t1.- Gestión de fabricantes.");
			System.out.println("\t2.- Gestión de concesionarios.");
			System.out.println("\t3.- Gestión de clientes.");
			System.out.println("\t4.- Gestión de coches.");
			System.out.println("\t5.- Gestión de ventas.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");
			
			
			opcionElegida = Utils.getIntConsola(0, 5);
			
			switch (opcionElegida) {
			case 0:
				System.out.println("\n!Qué tenga un feliz día!");
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
