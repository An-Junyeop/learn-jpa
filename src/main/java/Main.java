import model.DeliveryDTO;
import model.Member;
import model.Order;
import model.OrderStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // JOIN
            List<Order> resultList = em
                    .createQuery("select distinct o from Order o join fetch o.orderItems " +
                            "join fetch o.delivery")
                    .getResultList();


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
