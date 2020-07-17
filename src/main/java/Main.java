import model.Order;
import model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<OrderItem> oi = cq.from(OrderItem.class);

            Expression<Integer> maxCnt = cb.max(oi.<Integer>get("count"));
            Expression<Integer> minCnt = cb.min(oi.<Integer>get("count"));

            cq.multiselect(
                    oi.get("order").get("member").get("name"),
                    maxCnt,
                    minCnt
            );

            cq.groupBy(oi.get("order").get("member").get("id"))
                    .having(cb.gt(minCnt, 5));

            cq.orderBy(cb.asc(oi.get("order").get("member").get("id")));

            List<Object[]> result = em.createQuery(cq).getResultList();

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
