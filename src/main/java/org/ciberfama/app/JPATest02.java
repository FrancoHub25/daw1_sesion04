package org.ciberfama.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ciberfarma.modelo.Productos;

public class JPATest02 {

	public static void main(String[] args) {
		

		Productos p = new Productos();
		
		p.setCodProd("P0022");
		p.setDesProd("DestroySick");
		p.setStkProd(25);
		p.setPrecProd(2.50);
		p.setCodCategoria(2);
		p.setEstProd(1);
		p.setCodProvedor(1);
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(p);
		
		em.getTransaction().commit();
		
		System.out.println("Registro listo");
		em.close();
		
	}

}
