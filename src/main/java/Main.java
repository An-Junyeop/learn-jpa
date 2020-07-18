import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.sun.org.apache.xpath.internal.operations.Or;
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

            QOrder order1 = QOrder.order;
            QOrder subOrder = new QOrder("orderSub");

            query.from(order1)
                    .where(order1.orderDate.eq(
                            new JPASubQuery().from(subOrder).unique(subOrder.orderDate.max())
                    ))
                    .list(order1);


            QOrder order2 = new QOrder("order2");
            QMember subMember = new QMember("memberSub");

            query.from(order2)
                    .where(order2.member.in(
                            new JPASubQuery().from(subMember).where(subMember.name.startsWith("kim")).list(subMember)
                    ))
                    .list(order2);

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
