package libreria.servicio;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

/*

 */
public class EditorialServicio {

    public void crearEditrial(String nombre) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();
            Editorial edi = new Editorial();

            edi.setNombre(nombre);

            em.persist(edi);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Editorial> consultarEditorial(String nombre) throws Exception {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            List<Editorial> edit = em.createQuery("SELECT b FROM Editorial b WHERE b.nombre LIKE :nombre")
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
            return edit;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void modificarEditorial(Integer id, String nombre) throws Exception {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();

            Editorial edi = em.find(Editorial.class, id);

            edi.setNombre(nombre);

            em.merge(edi);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }

    }

    public void eliminarEditorial(Integer id) throws Exception {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();

            Editorial edi = em.find(Editorial.class, id);

            em.remove(edi);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }

    public static Editorial buscarEdiID(Integer id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        try {
            Editorial edit = em.find(Editorial.class, id);
            return edit;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se encontro la Editorial");
            return null;
        }
    }
}
