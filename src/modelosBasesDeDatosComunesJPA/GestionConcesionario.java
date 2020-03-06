package modelosBasesDeDatosComunesJPA;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sun.xml.xsom.impl.scd.ParseException;

import modelosBasesDeDatosComunesJPA.ventaDeCoches.Concesionario;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Fabricante;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Utils;

public class GestionConcesionario {

	public static void menuGestion() throws ParseException, java.text.ParseException, SQLException {

		int opcionElegida = -1;
		do {
			System.out.println("\n\t\t\tGESTIÓN DE CONCESIONARIOS");
			
			System.out.println("\n\t1.- Listado de concesionarios.");
			System.out.println("\t2.- Alta de concesionario.");
			System.out.println("\t3.- Modificación de concesionario.");
			System.out.println("\t4.- Baja de concesionario.");
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
		
		Query q = em.createNativeQuery("SELECT * FROM concesionario;", Concesionario.class);
		
		List<Concesionario> concesionarios = (List<Concesionario>) q.getResultList();
		
		for (Concesionario concesionario : concesionarios) {
			System.out.println("Concesionario: " + concesionario.getCif() + " nombre: " + concesionario.getNombre() + "localidad: " + concesionario.getLocalidad());
		}
		
		em.close();
	}
	/**
	 * 
	 */
	private static void creacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		Concesionario co = new Concesionario();
		co.setCif("12345678A");
		co.setNombre("Coches Rafa");
		co.setLocalidad("Checoslovaquia");
		
		em.getTransaction().begin();
		em.persist(co);
		em.getTransaction().commit();
		
		TypedQuery<Concesionario> q = em.createQuery("SELECT c FROM Concesionario as c", Concesionario.class);
		
		List<Concesionario> concesionarios = q.getResultList();
		
		for (Concesionario coEnLista : concesionarios) {
			System.out.println("Concesionario: " + coEnLista.getId() + " Nombre: " + coEnLista.getNombre() + " Localidad: " + coEnLista.getLocalidad());
		}
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void modificacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Concesionario> q = em.createQuery("SELECT c FROM Concesionario as c where c.cif = '12345678A'", Concesionario.class);
		
		List<Concesionario> concesionarios = q.getResultList();
		
		em.getTransaction().begin();
		for (Concesionario coEnLista : concesionarios) {
			coEnLista.setNombre("  Modificado   ");
			em.persist(coEnLista);
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

		TypedQuery<Concesionario> q = em.createQuery("SELECT c FROM Concesionario as c where c.cif = '12345678A'", Concesionario.class);
		
		List<Concesionario> concesionarios = q.getResultList();
		
		em.getTransaction().begin();
		for (Concesionario coEnLista : concesionarios) {
			em.remove(coEnLista);
		}
		em.getTransaction().commit();
		
		
		em.close();
	}

}
