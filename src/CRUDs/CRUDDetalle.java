package CRUDs;

import POJOs.Detalle;
import POJOs.Producto;
import java.math.BigDecimal;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author keyme
 */
public class CRUDDetalle {
public static boolean insert (int idProducto, Integer cantidad, BigDecimal precioProducto) {
    boolean bandera = false;
    Session session = HibernateUtil.hibernateUtil.getSessionFactory().openSession();
    Criteria criteria = session.createCriteria(Detalle.class);
    criteria.add(Restrictions.eq("cantidad", cantidad));
    criteria.add(Restrictions.eq("precioProducto", precioProducto));
    criteria.add(Restrictions.eq("a.idProducto", idProducto));
    Detalle insert = (Detalle)criteria.uniqueResult();
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
            session.save(insert);
            bandera=true;
        }
        transaction.commit();
    }
    catch (HibernateException e) {
        transaction.rollback();
        System.out.println("error=" +e);
    }
    finally {
        session.close();
    }
    return bandera;
}

    
}
