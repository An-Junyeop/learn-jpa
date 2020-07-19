import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
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

            QDelivery delivery = QDelivery.delivery;

            // setter에 접근하여 값을 매핑
            List<DeliveryDTO> result = query.distinct().from(delivery).list(
                    Projections.bean(DeliveryDTO.class,
                            delivery.id.as("id"),
                            delivery.status.as("status"))
            );

            // 필드 자체에 접근하여 값을 매핑
            /*List<DeliveryDTO> result1 = query.from(delivery).list(
                    Projections.fields(DeliveryDTO.class,
                            delivery.id.as("id"),
                            delivery.status.as("status"))
            );*/

            // 생성자에 접근하여 값을 매핑 (파라미터 순서가 같은 생성자 필요)
            /*List<DeliveryDTO> result2 = query.from(delivery).list(
                    Projections.constructor(DeliveryDTO.class,
                            delivery.id.as("id"),
                            delivery.status.as("status"))
            );*/

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
