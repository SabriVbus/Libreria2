package libreria;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicio.AutorServicio;
import libreria.servicio.ClienteServicio;
import libreria.servicio.EditorialServicio;
import libreria.servicio.LibroServicio;
import libreria.servicio.PrestamoServicio;

public class Libreria {

    /*

     */
    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        AutorServicio as = new AutorServicio();

////////////////Crear Crear//////////////
//        as.crearAutor("Jose");
//        as.crearAutor("Sabrina");
//        as.crearAutor("Pedro");
//        as.crearAutor("Yoana");
//        as.crearAutor("Brian");
////////////////Consultar Autor////////////        
////        System.out.println("Ingrese el autor a buscar");
////        String nombre = leer.next();
////        for (Autor a1 : as.consultaAutor(nombre)) {
////            System.out.println(a1);
////        }
////////////////Cambiar Autor//////////// 
////        System.out.println("Ingrese el autor a buscar");
////        String nombre = leer.next();
////        
////        System.out.println("ingrese el ide del autor");
////        Integer id=leer.nextInt();
////        as.modificarAutor(id, nombre);
///////////////////Borrar Autor///////////////
////        System.out.println("Ingrese el nombre del autor que quiere borrar");
////        Integer id=leer.nextInt();
////        
////        as.eliminarAutor(id);
//        EditorialServicio es = new EditorialServicio();
//
//    es.crearEditrial("Larusso");
//    es.crearEditrial("Chinchulin");
//    es.crearEditrial("Chihuha");
//    es.crearEditrial("Gomez");
//    es.crearEditrial("Dinamo");
////////////////Consultar Editor////////////        
////        System.out.println("Ingrese la editorial a buscar");
////        String nombre2 = leer.next();
////        for (Editorial e1 : es.consultarEditorial(nombre2)) {
////            System.out.print10087n(e1);
////        }
////////////////Cambiar Editor//////////// 
////        System.out.println("Ingrese el Editorial a buscar");
////        String nombre2 = leer.next();
////        
////        System.out.println("ingrese el ide del Editorial");
////        Integer id=leer.nextInt();
////        es.modificarEditorial(id, nombre2);
///////////////////Borrar Editor///////////////
////        System.out.println("Ingrese el nombre del autor que quiere borrar");
////        Integer id=leer.nextInt();
////        
////        es.eliminarEditorial(id);
//////////////Crear Libro///////////////////////////
//        LibroServicio ls = new LibroServicio();
//
//    ls.crearLibro();
////////////////////Modificar titulo///////////////////        
////ls.modificarLibro();
///////////////////Eliminar libro//////
////        System.out.println("Lista de libros actuales");
////        for (Libro lib : ls.mostrarListaLibro()) {
////            System.out.println(lib);
////        }
////        ls.eliminarLibro();
//////////////Consultar libro//////////
////       ls.consultaLibro();
//////////////Crear cliente///////////////////////////
//        ClienteServicio cs = new ClienteServicio();
//        cs.crearCliente();

PrestamoServicio ps = new PrestamoServicio();
// ps.nuevoPrestamo();
ps.devolucion();

    }

}
