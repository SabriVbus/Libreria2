package libreria.servicio;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import libreria.entidades.Cliente;

/*
private Long documento;
    private String nombre;
    private String apellido;
    private String domicilio;
    private Long telefono;
 */
public class ClienteServicio {
public void crearCliente(){
     Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();
            Cliente cli = new Cliente();
            
            System.out.println("CLIENTE NUEVO");

            System.out.println("DNI: ");
            cli.setDocumento(leer.nextLong());
            
            System.out.println("NOMBRE:");
            cli.setNombre(leer.next());
            
            System.out.println("APELLIDO:");
            cli.setApellido(leer.next());
            
            System.out.println("DOMICILIO:");
            cli.setDomicilio(leer.next());
            
            System.out.println("TELEFONO:");
            cli.setTelefono(leer.nextLong());

            em.persist(cli);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
}
public static Cliente buscarCliente(Long dni) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        try {
            Cliente edit = em.find(Cliente.class, dni);
            return edit;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se encontro el Cliente");
            return null;
        }
    }
}
