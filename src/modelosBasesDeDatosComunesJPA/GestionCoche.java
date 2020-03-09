package modelosBasesDeDatosComunesJPA;

import java.security.Principal;	
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sun.xml.xsom.impl.scd.ParseException;

import modelosBasesDeDatosComunesJPA.ventaDeCoches.Utils;
import tema7.gestionVenta.gestionVentaCoches.modelo.controladores.ControladorCoche;
import tema7.gestionVenta.gestionVentaCoches.modelo.controladores.ErrorBBDDException;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Fabricante;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Coche;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Concesionario;



public class GestionCoche {
	
	public static void menuGestion() throws ParseException, java.text.ParseException, SQLException, modelosBasesDeDatosComunesJPA.ventaDeCoches.controladores.ErrorBBDDException {

		int opcionElegida = -1;
		do {
			System.out.println("\n\t\t\tGESTIÓN DE COCHES");
			
			System.out.println("\n\t1.- Listado de coches.");
			System.out.println("\t2.- Alta de coches.");
			System.out.println("\t3.- Modificación de coches.");
			System.out.println("\t4.- Baja de coches.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");
			
			opcionElegida = Utils.getIntConsola(0, 4);
			
			switch (opcionElegida) {
			case 0:
				break;
			case 1:
				listadoEntidades();
				break;
			case 2:
				creacionEntidad();
			}
		} while (opcionElegida != 0);
	}

	/**
	 * 
	 */
	private static void listadoEntidades () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM coche;", Coche.class);
		
		List<Coche> coches = (List<Coche>) q.getResultList();
		
		for (Coche coche : coches) {
			System.out.println("Coche: " + coche.getId() + " modelo: " + coche.getModelo());
		}
		
		em.close();
	}
	
	/**
	 * @throws modelosBasesDeDatosComunesJPA.ventaDeCoches.controladores.ErrorBBDDException 
	 * 
	 */
	private static void creacionEntidad () throws modelosBasesDeDatosComunesJPA.ventaDeCoches.controladores.ErrorBBDDException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		Coche coc = new Coche();
		
		Fabricante fab = GestionFabricante.seleccionPorUsuario();
		coc.setFabricante(fab);
		
		em.getTransaction().begin();
		em.persist(coc);
		em.getTransaction().commit();
		
		TypedQuery<Coche> q = em.createQuery("SELECT coc FROM Coche as coc", Coche.class);
		
		List<Coche> coches = q.getResultList();
		
		for (Coche cocEnLista : coches) {
			System.out.println("Concesionario: " + cocEnLista.getId() + " Nombre: " + cocEnLista.getBastidor() + " Localidad: " + cocEnLista.getModelo());
		}
		
		em.close();
	}
	


}
