/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaproyectofinal;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 *
 * @author admin
 */
public class LogicaProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        BigDecimal precio = new BigDecimal(1199.99);
//        System.out.println(CRUDs.CRUDProducto.insert("Activo", "Refrigeradora LG", 25, precio, 1));
//          CRUDs.CRUDEmpleado.update(1, "Activo", "Marverick Joel Argueta Cifuentes", "Marve77", "12345678");
//          CRUDs.CRUDProducto.update(1, "refri mabe", 35, precio, 1);
          for(int i =0; i< CRUDs.CRUDProducto.universo().size(); i++){
              System.out.println("Nombre Producto "+CRUDs.CRUDProducto.universo().get(i).getNombreProducto());
              System.out.println("Cantidad "+CRUDs.CRUDProducto.universo().get(i).getExistecnia());
              System.out.println("Precio "+CRUDs.CRUDProducto.universo().get(i).getPrecioProducto());
          }
    }
    
}
