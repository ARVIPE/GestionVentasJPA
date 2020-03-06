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

import modelosBasesDeDatosComunesJPA.ventaDeCoches.Fabricante;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Utils;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.controladores.ErrorBBDDException;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Coche;



public class GestionFabricante {
	
	public static void menuGestion() throws ParseException, java.text.ParseException, SQLException {

		int opcionElegida = -1;
		do {
			System.out.println("\n\t\t\tGESTIÓN DE FABRICANTES");
			
			System.out.println("\n\t1.- Listado de fabricantes.");
			System.out.println("\t2.- Alta de fabricante.");
			System.out.println("\t3.- Modificación de fabricante.");
			System.out.println("\t4.- Baja de fabricante.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");
			
			opcionElegida = Utils.getIntConsola(0, 4);
			
			if(opcionElegida == 1) {
				listadoEntidades();
			}
			if(opcionElegida == 2) {
				creacionEntidad();
			}
			if(opcionElegida == 3) {
				modificacionEntidad();
			}
			if(opcionElegida == 4) {
				eliminacionEntidad();
			}
		} while (opcionElegida != 0);
	}

	
	/**
	 * 
	 */
	private static void listadoEntidades () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM fabricante;", Fabricante.class);
		
		List<Fabricante> fabricantes = (List<Fabricante>) q.getResultList();
		
		for (Fabricante fabricante : fabricantes) {
			System.out.println("Fabricante: " + fabricante.getCif() + " modelo: " + fabricante.getNombre());
		}
		
		em.close();
	}
	/**
	 * 
	 */
	private static void creacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		Fabricante fab = new Fabricante();
		fab.setCif("12345678A");
		fab.setNombre("Coches Rafa");
		
		em.getTransaction().begin();
		em.persist(fab);
		em.getTransaction().commit();
		
		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		for (Fabricante fabEnLista : fabricantes) {
			System.out.println("Fabricante: " + fabEnLista.getId() + " CIF: " + fabEnLista.getCif() + " Nombre: " + fabEnLista.getNombre());
		}
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void modificacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f where f.cif = '12345678A'", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Fabricante fabEnLista : fabricantes) {
			fabEnLista.setNombre("Modificado");
			em.persist(fabEnLista);
		}
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void eliminacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f where f.cif = '12345678A'", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Fabricante fabEnLista : fabricantes) {
			em.remove(fabEnLista);
		}
		em.getTransaction().commit();
		
		
		em.close();
	}
	
	

}
