import com.mysema.query.jpa.impl.JPAQuery;
import model.Order;
import model.QMember;
import model.QOrder;
import model.QOrderItem;

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
            QOrder qOrder = new QOrder("o");
            QOrderItem qOrderItem = new QOrderItem("oi");
            QMember qMember = new QMember("m");

            query.from(qOrder)
                    .innerJoin(qOrder.member, qMember)
                    .leftJoin(qOrder.orderItems, qOrderItem)
                    .on(qOrderItem.count.gt(2))
                    .list(qOrder);


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
