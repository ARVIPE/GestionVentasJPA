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
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Venta;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.controladores.ErrorBBDDException;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Cliente;
import modelosBasesDeDatosComunesJPA.ventaDeCoches.Coche;



public class GestionVenta {
	
	public static void menuGestion() throws ParseException, java.text.ParseException, SQLException {

		int opcionElegida = -1;
		do {
			System.out.println("\n\t\t\tGESTIÓN DE VENTA");
			
			System.out.println("\n\t1.- Listado de ventas.");
			System.out.println("\t2.- Alta de venta.");
			System.out.println("\t3.- Modificación de venta.");
			System.out.println("\t4.- Baja de venta.");
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
		
		Query q = em.createNativeQuery("SELECT * FROM venta;", Venta.class);
		
		List<Venta> ventas = (List<Venta>) q.getResultList();
		
		for (Venta venta : ventas) {
			System.out.println(" fecha: " + venta.getFecha() + " precioVenta: " + venta.getPrecioVenta());
		}
		
		em.close();
	}
	/**
	 * 
	 */
	private static void creacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		Venta v = new Venta();
		v.setPrecioVenta(248054);
		
		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();
		
		TypedQuery<Venta> q = em.createQuery("SELECT v FROM Venta as v", Venta.class);
		
		List<Venta> ventas = q.getResultList();
		
		for (Venta venEnLista : ventas) {
			System.out.println(" precioVenta: " + venEnLista.getPrecioVenta());
		}
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void modificacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VentaDeCoches");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Venta> q = em.createQuery("SELECT v FROM Venta as v where v.precioVenta = '248054'", Venta.class);
		
		List<Venta> ventas = q.getResultList();
		
		em.getTransaction().begin();
		for (Venta venEnLista : ventas) {
			venEnLista.setPrecioVenta(204842);
			em.persist(venEnLista);
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

		TypedQuery<Venta> q = em.createQuery("SELECT v FROM Venta as v where v.precioVenta = '248054' or '204842'", Venta.class);
		
		List<Venta> ventas = q.getResultList();
		
		em.getTransaction().begin();
		for (Venta venEnLista : ventas) {
			em.remove(venEnLista);
		}
		em.getTransaction().commit();
		
		
		em.close();
	}
	
	

}
