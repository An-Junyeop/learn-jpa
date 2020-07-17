import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;
import model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<OrderItem> query = cb.createQuery(OrderItem.class);
            Subquery<Double> subQuery = query.subquery(Double.class);

            Root<OrderItem> oi2 = subQuery.from(OrderItem.class);
            subQuery.select(cb.avg(oi2.<Integer>get("count")));

            Root<OrderItem> oi = query.from(OrderItem.class);
            query.select(oi)
                    .where(cb.ge(oi.<Number>get("count"), subQuery));

            List<OrderItem> result = em.createQuery(query).getResultList();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
