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
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Fabricante;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Coche;



public class GestionCoche {
	
	public static void menuGestion() throws ParseException, java.text.ParseException, SQLException {

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
//	/**
//	 * 
//	 */
//	private static void creacionEntidad () {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");
//
//		EntityManager em = entityManagerFactory.createEntityManager();
//
//		Coche co = new Coche();
//		co.setCif("12345678A");
//		.setNombre("Coches Rafa");
//		
//		em.getTransaction().begin();
//		em.persist(fab);
//		em.getTransaction().commit();
//		
//		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f", Fabricante.class);
//		
//		List<Fabricante> fabricantes = q.getResultList();
//		
//		for (Fabricante fabEnLista : fabricantes) {
//			System.out.println("Fabricante: " + fabEnLista.getId() + " CIF: " + fabEnLista.getCif() + " Nombre: " + fabEnLista.getNombre());
//		}
//		
//		em.close();
//	}
	
}
