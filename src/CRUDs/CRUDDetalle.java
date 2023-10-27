package CRUDs;

import POJOs.Detalle;
import POJOs.Producto;
import POJOs.Venta;
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

    public static boolean insert(Boolean estado,Integer idProducto, Integer idFactura, Integer cantidad, BigDecimal precioProducto, BigDecimal total) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.eq("estado",false));
        Detalle insert = (Detalle) criteria.uniqueResult();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (insert == null) {
                insert = new Detalle();
                Producto producto = new Producto();
                Venta venta = new Venta();
                insert.setEstado(estado);
                insert.setProducto(producto);
                insert.setCantidad(cantidad);
                insert.setPrecioProducto(precioProducto);
                producto.setIdProducto(idProducto);
                venta.setIdFactura(idFactura);
                insert.setVenta(venta);
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

    public static Detalle select(Boolean estado,Integer factura) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.eq("estado", estado));
        Detalle select = (Detalle) criteria.uniqueResult();
        if (select == null) {
            Integer id;
            select = new Detalle();
            Venta venta = new Venta();
            venta.setIdFactura(factura);
            select.setVenta(venta);
            select.getCantidad();
            select.getTotal();
        }
        session.close();
        return select;
    }

    public static boolean update(Integer cantidad,BigDecimal total) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.eq("estado", false));
        Detalle update = (Detalle) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setCantidad(cantidad);
                update.setTotal(total);
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
        public static boolean updateEstado(Boolean estado) {
        boolean bandera = false;
        Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.eq("estado", false));
        Detalle update = (Detalle) criteria.uniqueResult();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (update != null) {
                update.setEstado(estado);
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
