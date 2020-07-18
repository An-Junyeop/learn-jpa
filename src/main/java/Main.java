import com.mysema.query.jpa.impl.JPAQuery;
import static model.QOrder.order;
import static model.QOrderItem.orderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQuery query = new JPAQuery(em);

            query.from(orderItem)
                    .groupBy(orderItem.order.id)
                    .having(orderItem.count.sum().gt(3))
                    .list(orderItem);

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
