package libreria.servicio;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

/*
consulta, creación, modificación y eliminación).
 */
public class AutorServicio {

    public void crearAutor(String nombre) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();
            Autor autor = new Autor();

            System.out.println("Ingrese el nombre del autor a agregar");
            autor.setNombre(leer.next());

            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public List<Autor> consultaAutor(String nombre) throws Exception {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

            List<Autor> autor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();

            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void modificarAutor(Integer id, String nombre) throws Exception {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();

            Autor autor = em.find(Autor.class, id);

            autor.setNombre(nombre);

            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public void eliminarAutor(Integer id) throws Exception {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
            em.getTransaction().begin();

            Autor autor = em.find(Autor.class, id);

            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static Autor buscarAutorID(Integer id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        try {
            Autor autor = em.find(Autor.class, id);
            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se encontro la Editorial");
            return null;
        }
    }
}
