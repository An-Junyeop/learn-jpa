import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.Projections;
import com.sun.org.apache.xpath.internal.operations.Or;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            JPAQuery query = new JPAQuery(em);

            QItem item = QItem.item;
            JPAUpdateClause updateClause = new JPAUpdateClause(em, item);
            updateClause.where(item.name.eq("Book"))
                    .set(item.price, item.price.add(100))
                    .execute();

            QOrder order = QOrder.order;
            JPADeleteClause deleteClause = new JPADeleteClause(em, order);
            deleteClause.where(order.status.eq(OrderStatus.ORDER))
                    .execute();


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
