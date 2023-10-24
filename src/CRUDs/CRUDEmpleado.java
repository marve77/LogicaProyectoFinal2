/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import HibernateUtil.hibernateUtil;
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
public class CRUDEmpleado {
        public static boolean insert(String estado, String nombreEmpleado, String usuario,String contrasenia) {
        boolean bandera = false;
        boolean est=false;
        if(estado.equals("Activo")){
            est=true;
            if(estado.equals("Inactivo")){
                est=false;
            }
        }
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Empleado.class);
        criteria.add(Restrictions.eq("nombreEmpleado", nombreEmpleado));
        criteria.add(Restrictions.eq("usuario", usuario));
        criteria.add(Restrictions.eq("contrasenia", contrasenia));
        Empleado insert = (Empleado) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Empleado();
                insert.setEstado(est);
                insert.setNombreEmpleado(nombreEmpleado);
                insert.setUsuario(usuario);
                insert.setContrasenia(contrasenia);
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
 public static boolean update(Integer idEmpelado, String nombreEmpelado,String usuario,String contrasenia) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Empleado.class);
        criteria.add(Restrictions.eq("idEmpelado", idEmpelado));
        Empleado update = (Empleado) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setNombreEmpleado(nombreEmpelado);
                update.setUsuario(usuario);
                update.setContrasenia(contrasenia);
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
       public static boolean anular(Integer idEmpelado, String estado) {
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
        Criteria criteria = session.createCriteria(Empleado.class);
        criteria.add(Restrictions.eq("idCliente", idEmpelado));
        Empleado update = (Empleado) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setEstado(est);
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
     public static List<Empleado> universo(){
        Session session =HibernateUtil.hibernateUtil.getSessionFactory().getCurrentSession();
        List <Empleado> lista=null;
        
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Empleado.class);
            criteria.addOrder(Order.desc("idEmpleado"));
            criteria.setMaxResults(500);
            lista = criteria.list();
            
        } catch (Exception e) {
            System.out.println("ERROR "+ e);
        }finally{
            session.getTransaction().commit();
        }
        return lista;
    }
     public static Empleado select(Integer idEmpleado) {
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Empleado.class);
        criteria.add(Restrictions.eq("idEmpleado", idEmpleado));
        Empleado select = (Empleado) criteria.uniqueResult();
        if (select == null) {
            select = new Empleado();
            select.setIdEmpleado(0);
            select.setNombreEmpleado("0");
        }
        session.close();
        return select;
    }
}
