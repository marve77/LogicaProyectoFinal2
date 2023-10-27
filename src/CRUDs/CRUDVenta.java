/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDs;

import POJOs.Cliente;
import POJOs.Detalle;
import POJOs.Empleado;
import POJOs.Venta;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author keyme
 */
public class CRUDVenta {

    public static boolean insert(Integer idCliente, Integer usuario) {
        boolean bandera = false;
        Date fecha = new Date();
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
         Criteria criteria = session.createCriteria(Venta.class);
         criteria.add(Restrictions.eq("estado",false));
        Venta insert = (Venta) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Venta();
                Empleado empleado = new Empleado();
                Cliente cliente = new Cliente();
                empleado.setIdEmpleado(usuario);
                insert.setCliente(cliente);
                cliente.setIdCliente(idCliente);
                insert.setEmpleado(empleado);
                insert.setIdEmpleado(empleado.getIdEmpleado());
                insert.setFechaVenta(fecha);
                insert.setFechaIngresa(fecha);
                insert.setEstado(false);
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

    public static boolean eliminar(Integer idFactura) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Venta.class);
        criteria.add(Restrictions.eq("idFactura", idFactura));
        Venta eliminar = (Venta) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (eliminar != null) {
                session.delete(eliminar);
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

    public static List<Venta> universo() {
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().getCurrentSession();
        List<Venta> lista = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Venta.class);
            criteria.createAlias("cliente", "b");
            criteria.addOrder(Order.desc("idFactura"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        } catch (HibernateException e) {
            System.out.println("error" + e);
        } finally {
            session.getTransaction().commit();
        }
        return lista;
    }

    public static POJOs.Venta select(boolean estado) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(POJOs.Venta.class);
        criteria.add(Restrictions.eq("estado", estado));
        POJOs.Venta select = (POJOs.Venta) criteria.uniqueResult();
        if (select == null) {
            select = new Venta();
            select.setIdFactura(0);
            select.getEstado();
        }
        session.close();
        return select;
    }
    public static boolean update(Integer idVenta) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Venta.class);
        criteria.add(Restrictions.eq("idVenta", idVenta));
        Venta update = (Venta) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setEstado(true);
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

