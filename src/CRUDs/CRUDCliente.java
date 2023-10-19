/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Cliente;
import POJOs.Empleado;
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
public class CRUDCliente {
      public static List<Cliente> universo(){
        Session session =HibernateUtil.hibernateUtil.getSessionFactory().getCurrentSession();
        List <Cliente> lista=null;
        
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Cliente.class);
            criteria.add(Restrictions.eq("estado", true));
            criteria.addOrder(Order.desc("idCliente"));
            criteria.setMaxResults(500);
            lista = criteria.list();
            
        } catch (Exception e) {
            System.out.println("ERROR "+ e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
    public static boolean insert(String estado, String nombreCliente,String apellidoCliente,String nitCliente,String telefono, Integer usuario) {
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
        Criteria criteria = session.createCriteria(Cliente.class);        
        criteria.add(Restrictions.eq("nombreCliente", nombreCliente));
        criteria.add(Restrictions.eq("apellidoCliente",apellidoCliente));
        criteria.add(Restrictions.eq("nitCliente",nitCliente));
        criteria.add(Restrictions.eq("telefono",telefono));
        Cliente insert = (Cliente) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Cliente();
                Empleado empleado= new Empleado();
                empleado.setIdEmpleado(usuario);
                insert.setEstado(est);
                insert.setNombreCliente(nombreCliente);
                insert.setApellidoCliente(apellidoCliente);
                insert.setNitCliente(nitCliente);
                insert.setTelefono(telefono);
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
     
    public static boolean update(Integer idCliente, String nombreCliente,String apellidoCliente,String nitCliente,String telefono, Integer usuario) {
        boolean bandera = false;
        Date fecha = new Date();
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("idCliente", idCliente));
        Cliente update = (Cliente) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setNombreCliente(nombreCliente);
                update.setApellidoCliente(apellidoCliente);
                update.setNitCliente(nitCliente);
                update.setTelefono(telefono);
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
       public static boolean anular(Integer idCliente, String estado,Integer usuario) {
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
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("idCliente", idCliente));
        Cliente update = (Cliente) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setEstado(est);
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
    
}
