package libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*

 */
@Entity
public class Libro {

    @Id
    private Long isbn;
    private String titulo;
    private Integer año;
    private Integer ejemplanes;
    private Integer prestados;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;

    public Libro(Long isbn, String titulo, Integer año, Integer ejemplanes, Integer prestados, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.año = año;
        this.ejemplanes = ejemplanes;
        this.prestados = prestados;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getEjemplanes() {
        return ejemplanes;
    }

    public void setEjemplanes(Integer ejemplanes) {
        this.ejemplanes = ejemplanes;
    }

    public Integer getPrestados() {
        return prestados;
    }

    public void setPrestados(Integer prestados) {
        this.prestados = prestados;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn= " + isbn + ", titulo= " + titulo + ", año= " + año + ", ejemplanes= " + ejemplanes + ", prestados= " + prestados + ", autor= " + autor + ", editorial= " + editorial + '}';
    }
    
    
}
