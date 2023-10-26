package CRUDs;

import POJOs.Detalle;
import POJOs.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author keyme
 */
public class CRUDDetalle {

    public static List<Detalle> universo() {
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().getCurrentSession();
        List<Detalle> lista = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Detalle.class);
            criteria.createAlias("producto", "a");
            criteria.addOrder(Order.desc("idDetalle"));
            criteria.setMaxResults(500);
            lista = criteria.list();
        } catch (HibernateException e) {
            System.out.println("eerror=" + e);
        } finally {
            session.getTransaction().commit();
        }
        return lista;
    }

    public static boolean insert(Integer idProducto, Integer cantidad, BigDecimal precioProducto,BigDecimal total) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.eq("cantidad", cantidad));
        criteria.add(Restrictions.eq("precioProducto", precioProducto));
        criteria.createAlias("producto", "a");
        criteria.add(Restrictions.eq("a.idProducto", idProducto));
        Detalle insert = (Detalle) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Detalle();
                Producto producto = new Producto();
                insert.setProducto(producto);
                insert.setCantidad(cantidad);
                insert.setPrecioProducto(precioProducto);
                producto.setIdProducto(idProducto);
                insert.setTotal(total);
                session.save(insert);
                bandera = true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("error=" + e);
        } finally {
            session.close();
        }
        return bandera;
    }

    public static boolean eliminar(Integer idDetalle) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.eq("idDetalle", idDetalle));
        Detalle eliminar = (Detalle) criteria.uniqueResult();
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
            System.out.println("error= " + e);
        } finally {
            session.close();
        }
        return bandera;
    }


}
