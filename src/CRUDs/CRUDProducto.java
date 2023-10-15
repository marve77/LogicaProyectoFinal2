/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Empleado;
import POJOs.Producto;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author admin
 */
public class CRUDProducto {
    
    public static List<Producto> universo(){
        Session session =HibernateUtil.hibernateUtil.getSessionFactory().getCurrentSession();
        List <Producto> lista=null;
        
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Producto.class);
            criteria.add(Restrictions.eq("estado", true));
            criteria.addOrder(Order.desc("idProducto"));
            criteria.setMaxResults(500);
            lista = criteria.list();
            
        } catch (Exception e) {
            System.out.println("ERROR "+ e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    public static boolean insert(String estado, String nombreProducto, Integer existencia,BigDecimal precioProducto,Integer usuario) {
        boolean bandera = false;
        boolean est=false;
        Date fecha = new Date() ;
        if(estado.equals("Activo")){
            est=true;
            if(estado.equals("Inactivo")){
                est=false;
            }
        }
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Producto.class);        
        criteria.add(Restrictions.eq("nombreProducto", nombreProducto));
        criteria.add(Restrictions.eq("precioProducto", precioProducto));
        Producto insert = (Producto) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Producto();
                Empleado empleado= new Empleado();
                empleado.setIdEmpleado(usuario);
                insert.setEstado(est);
                insert.setNombreProducto(nombreProducto);
                insert.setExistecnia(existencia);
                insert.setPrecioProducto(precioProducto);
                insert.setEmpleadoByUsuarioIngresa(empleado);
                insert.setFechaIngresa(fecha);
                session.save(insert);
                bandera = true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("error" + e);
        } finally {
            session.close();
        }
        return bandera;
    }
     
    public static boolean update(Integer idProducto, String nombreProducto, Integer existencia, BigDecimal precioProducto, Integer usuario) {
        boolean bandera = false;
        Date fecha = new Date();
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("idProducto", idProducto));
        Producto update = (Producto) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setNombreProducto(nombreProducto);
                update.setExistecnia(existencia);
                update.setPrecioProducto(precioProducto);
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(usuario);
                update.setEmpleadoByUsuarioModifica(empleado);
                update.setFechaModifica(fecha);
                session.update(update);
                bandera = true;
            }
            transaction.commit();

        } catch (Exception e) {
            System.out.println("ERROR" + e);
            transaction.rollback();
        } finally {
            session.close();
        }

        return bandera;

    }
       public static boolean anular(Integer idProducto, String estado) {
        boolean bandera = false;
        boolean est=false;
        Date fecha = new Date();
            if(estado.equals("Activo")){
                est=true;
                if(estado.equals("Inactivo")){
                    est=false;
            }
        }
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("idProducto", idProducto));
        Producto update = (Producto) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setEstado(bandera);
                Empleado empleado = new Empleado();
                empleado.setEstado(est);
                update.setEmpleadoByUsuarioModifica(empleado);
                update.setFechaModifica(fecha);
                session.update(update);
                bandera = true;
            }
            transaction.commit();

        } catch (Exception e) {
            System.out.println("ERROR" + e);
            transaction.rollback();
        } finally {
            session.close();
        }

        return bandera;

    }


}
