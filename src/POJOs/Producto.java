package POJOs;
// Generated 26/10/2023 04:27:11 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private int idProducto;
     private Empleado empleadoByUsuarioModifica;
     private Empleado empleadoByUsuarioIngresa;
     private Boolean estado;
     private String nombreProducto;
     private Integer existecnia;
     private BigDecimal precioProducto;
     private Date fechaIngresa;
     private Date fechaModifica;
     private Set detalles = new HashSet(0);

    public Producto() {
    }

	
    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }
    public Producto(int idProducto, Empleado empleadoByUsuarioModifica, Empleado empleadoByUsuarioIngresa, Boolean estado, String nombreProducto, Integer existecnia, BigDecimal precioProducto, Date fechaIngresa, Date fechaModifica, Set detalles) {
       this.idProducto = idProducto;
       this.empleadoByUsuarioModifica = empleadoByUsuarioModifica;
       this.empleadoByUsuarioIngresa = empleadoByUsuarioIngresa;
       this.estado = estado;
       this.nombreProducto = nombreProducto;
       this.existecnia = existecnia;
       this.precioProducto = precioProducto;
       this.fechaIngresa = fechaIngresa;
       this.fechaModifica = fechaModifica;
       this.detalles = detalles;
    }
   
    public int getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public Empleado getEmpleadoByUsuarioModifica() {
        return this.empleadoByUsuarioModifica;
    }
    
    public void setEmpleadoByUsuarioModifica(Empleado empleadoByUsuarioModifica) {
        this.empleadoByUsuarioModifica = empleadoByUsuarioModifica;
    }
    public Empleado getEmpleadoByUsuarioIngresa() {
        return this.empleadoByUsuarioIngresa;
    }
    
    public void setEmpleadoByUsuarioIngresa(Empleado empleadoByUsuarioIngresa) {
        this.empleadoByUsuarioIngresa = empleadoByUsuarioIngresa;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public String getNombreProducto() {
        return this.nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public Integer getExistecnia() {
        return this.existecnia;
    }
    
    public void setExistecnia(Integer existecnia) {
        this.existecnia = existecnia;
    }
    public BigDecimal getPrecioProducto() {
        return this.precioProducto;
    }
    
    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }
    public Date getFechaIngresa() {
        return this.fechaIngresa;
    }
    
    public void setFechaIngresa(Date fechaIngresa) {
        this.fechaIngresa = fechaIngresa;
    }
    public Date getFechaModifica() {
        return this.fechaModifica;
    }
    
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }
    public Set getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(Set detalles) {
        this.detalles = detalles;
    }




}


