package libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/*

 */
@Entity
public class Cliente {
    
    @Id
    private Long documento;
    private String nombre;
    private String apellido;
    private String domicilio;
    private Long telefono;

    public Cliente() {
    }

    public Cliente(Long documento, String nombre, String apellido, String domicilio, Long telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "documento= " + documento + ", nombre= " + nombre + ", apellido= " + apellido + ", domicilio= " + domicilio + ", telefono= " + telefono + '}';
    }

    
    
    

}
