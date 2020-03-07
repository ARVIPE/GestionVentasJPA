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
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Cliente;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Coche;



public class GestionCliente {
	
	public static void menuGestion() throws ParseException, java.text.ParseException, SQLException {

		int opcionElegida = -1;
		do {
			System.out.println("\n\t\t\tGESTIÓN DE CLIENTE");
			
			System.out.println("\n\t1.- Listado de clientes.");
			System.out.println("\t2.- Alta de cliente.");
			System.out.println("\t3.- Modificación de cliente.");
			System.out.println("\t4.- Baja de cliente.");
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
		
		Query q = em.createNativeQuery("SELECT * FROM cliente;", Cliente.class);
		
		List<Cliente> clientes = (List<Cliente>) q.getResultList();
		
		for (Cliente cliente : clientes) {
			System.out.println("Cliente: " + cliente.getNombre() + " apellido: " + cliente.getApellidos() + " localidad: " + cliente.getLocalidad() +
					" dniNie: " + cliente.getDniNie() + " fechaNac: " + cliente.getFechaNac());
		}
		
		em.close();
	}
	/**
	 * 
	 */
	private static void creacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		Cliente cli = new Cliente();
		cli.setNombre("Arturo");
		cli.setApellidos("Vicente Perez");
		cli.setLocalidad("Cordoba");
		cli.setDniNie("31031987L");
		
		
		em.getTransaction().begin();
		em.persist(cli);
		em.getTransaction().commit();
		
		TypedQuery<Cliente> q = em.createQuery("SELECT cli FROM Cliente as cli", Cliente.class);
		
		List<Cliente> clientes = q.getResultList();
		
		for (Cliente cliEnLista : clientes) {
			System.out.println("Cliente: " + cliEnLista.getNombre() + " apellido: " + cliEnLista.getApellidos() + " localidad: " + cliEnLista.getLocalidad() +
					" dniNie: " + cliEnLista.getDniNie() + " fechaNac: " + cliEnLista.getFechaNac());
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
