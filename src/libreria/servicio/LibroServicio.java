package libreria.servicio;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;


/*

 */
public class LibroServicio {

    public void crearLibro() throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        em.getTransaction().begin();

        Libro libro = new Libro();
        try {

            System.out.println("Ingrese los siguiente datos");

            System.out.println("Ingrese el ISBN");
            libro.setIsbn(leer.nextLong());

            System.out.println("Ingrese el titulo del libro");
            libro.setTitulo(leer.next());

            System.out.println("Ingrese el año");
            libro.setAño(leer.nextInt());

            System.out.println("Ingrese cantidad de ejemplares");
            libro.setEjemplanes(leer.nextInt());

            System.out.println("Ingrese cantidad de prestados");
            libro.setPrestados(leer.nextInt());

            System.out.println("ID Autor: ");
            libro.setAutor(AutorServicio.buscarAutorID(leer.nextInt()));

            System.out.println("ID Editorial: ");
            libro.setEditorial(EditorialServicio.buscarEdiID(leer.nextInt()));

            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!! ingreso datos que no corresponden. Intente de nuevo");
        }
    }

    public void consultaLibro() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("CONSULTAR LIBRO");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Elija la opcion de como consultar libro");
        System.out.println("1- Por Titulo");
        System.out.println("2- Por ISBN");
        int op = leer.nextInt();
        switch (op) {
            case 1:

                System.out.println("Ingrese el Titulo");
                String titulo = leer.next();
                List<Libro> libr = em.createQuery("SELECT a FROM Libro a WHERE a.titulo LIKE :titulo")
                        .setParameter("titulo", "%" + titulo + "%")
                        .getResultList();

                for (Libro libros : libr) {
                    System.out.println(libros);
                }

                break;
            case 2:

                System.out.println("Ingrese el ISBN");
                Long isbn = leer.nextLong();
                Libro lib = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.isbn = :isbn")
                        .setParameter("isbn", isbn)
                        .getSingleResult();
                System.out.println(lib);
                break;
            default:
                System.out.println("Opcion incorrecta intente de nuevo");
                break;
        }

    }

    public void modificarLibro() throws Exception {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        em.getTransaction().begin();

        System.out.println("Que atribut quiere modificar?");
        System.out.println("1- Titulo");
        System.out.println("2- Año");
        System.out.println("3- Cantidad de ejemplares");
        System.out.println("4- Autor");
        System.out.println("5- Editorial");
        System.out.println("6- Todo");

        int opc = leer.nextInt();

        System.out.println("ingrese el isbn del Libro");
        Long isbn = leer.nextLong();
        Libro libro = em.find(Libro.class, isbn);
        try {
            switch (opc) {
                case 1:
                    System.out.println("Ingrese el nuevo Titulo");
                    libro.setTitulo(leer.next());
                    em.merge(libro);
                    em.getTransaction().commit();
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo año");
                    libro.setAño(leer.nextInt());
                    em.merge(libro);
                    em.getTransaction().commit();
                    break;
                case 3:
                    System.out.println("Ingrese la nueva cantidad de libros");
                    libro.setEjemplanes(leer.nextInt());
                    em.merge(libro);
                    em.getTransaction().commit();
                    break;
                case 4:
                    System.out.println("Ingrese el id del Autor");
                    libro.setAutor(AutorServicio.buscarAutorID(leer.nextInt()));
                    em.merge(libro);
                    em.getTransaction().commit();
                    break;
                case 5:
                    System.out.println("Ingrese el id del Editorial");
                    libro.setEditorial(EditorialServicio.buscarEdiID(leer.nextInt()));
                    em.merge(libro);
                    em.getTransaction().commit();
                    break;
                case 6:
                    System.out.println("Ingrese el nuevo Titulo");
                    libro.setTitulo(leer.next());

                    System.out.println("Ingrese el nuevo año");
                    libro.setAño(leer.nextInt());

                    System.out.println("Ingrese la nueva cantidad de libros");
                    libro.setEjemplanes(leer.nextInt());

                    System.out.println("Ingrese el id del Autor");
                    libro.setAutor(AutorServicio.buscarAutorID(leer.nextInt()));

                    System.out.println("Ingrese el id del Editorial");
                    libro.setEditorial(EditorialServicio.buscarEdiID(leer.nextInt()));

                    em.merge(libro);
                    em.getTransaction().commit();
                    break;

                default:
                    System.out.println("Opcion incorrecta, intente de nuevo");

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public void eliminarLibro() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        em.getTransaction().begin();

        System.out.println("Ingrese el ISBN del libro a eliminar");
        long isbn = leer.nextLong();
        Libro libro = em.find(Libro.class, isbn);

        em.remove(libro);
        em.getTransaction().commit();

    }

    public List<Libro> mostrarListaLibro() {

        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        List<Libro> librito = (List<Libro>) em.createQuery("SELECT b FROM Libro b ")
                .getResultList();

        return librito;

    }

    public static Libro buscarLibro(Long isbn) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
        try {
            Libro edit = em.find(Libro.class, isbn);
            if (edit != null) {

                if (edit.getEjemplanes() > 1) {
                    edit.setEjemplanes(edit.getEjemplanes() -1);
                    edit.setPrestados(edit.getPrestados() + 1);
                } else {
                    System.out.println("Error, ejemplares no disponibles");
                }

                em.getTransaction().begin();
                em.merge(edit);
                em.getTransaction().commit();
            } else {
                System.out.println("No exite ese libro");
            }
            return edit;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se encontro el Libro");
            return null;
        }
    }
}
