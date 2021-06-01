package libreria.servicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;


/*
crear prestamos, generar un prestamo que tenga mas de un libro
devolucion libro

 */
public class PrestamoServicio {

    public void nuevoPrestamo() throws Exception {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        String resp;
        Date rango;
        Date hoy = new Date();

        try {
            Prestamo pr = new Prestamo();

            System.out.println("INGRESE LOS SIGUIENTES DATOS PARA UN PRESTAMO NUEVO");
            System.out.println("A que cliente se lo prestará. Ingrese DNI");
            pr.setCliente(ClienteServicio.buscarCliente(leer.nextLong()));

            do {
                EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
                System.out.println("Cuantos dias va a ser el prestamo");
                int cantidad = leer.nextInt();
                rango = sumarDias(hoy, cantidad);

//            System.out.println(hoy.getDate()+ " / " + (hoy.getMonth()+1) + " / " + (hoy.getYear()+1900));
//            System.out.println(rango.getDate()+ " / " + (rango.getMonth()+1) + " / " + (rango.getYear()+1900));
                pr.setFecha(hoy);
                pr.setDevolucion(rango);

                System.out.println("Ingrese el ISBN del libro a prestar");
                pr.setLibro(LibroServicio.buscarLibro(leer.nextLong()));

                em.getTransaction().begin();
                em.persist(pr);
                em.getTransaction().commit();
                em.close();

                System.out.println("Quiere prestar otro libro? SI/No");
                resp = leer.next();

            } while (resp.equalsIgnoreCase("si"));

        } catch (Exception e) {
//            em.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error en el ingreso del Nuevo Cliente");
        }

    }

    private Date sumarDias(Date hoy, int cantDias) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(hoy); // Configuramos la fecha que se recibe

        calendar.add(Calendar.DAY_OF_YEAR, cantDias);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public void devolucion() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

        System.out.println("Ingrese el DNI del cliente");
        Long dni = leer.nextLong();

        Cliente cliente = em.find(Cliente.class, dni);
        
        List<Prestamo> lista = em.createQuery("SELECT c FROM Prestamo c WHERE c.cliente = :dni")
                .setParameter("dni", cliente)
                .getResultList();
        if (lista.isEmpty()){
            System.out.println("No vas a encontrar un presta con ese DNI");
        }else{
        for (Prestamo prestamo : lista) {
            
            System.out.println(prestamo.getId() + " / " + prestamo.getLibro()); 
        }
        }
        
        System.out.println("Que libro devuelve. Elija un ID de la lista");
        Integer encotrar = leer.nextInt();
        Prestamo pr = em.find(Prestamo.class, encotrar);
        
        Libro libro = em.find(Libro.class, pr.getLibro().getIsbn());
        
        libro.setEjemplanes(libro.getEjemplanes()+1);
        libro.setPrestados(libro.getPrestados()-1);
        
        em.getTransaction().begin();
        em.merge(libro);
        em.remove(pr);
        em.getTransaction().commit();
        
    }
}
