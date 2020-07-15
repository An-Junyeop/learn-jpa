import model.DeliveryDTO;
import model.Member;
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
            TypedQuery<Object[]> query = em.createQuery("select m, o from Member m left join Order o " +
                    "on o.status = :status", Object[].class);

            List<Object[]> result = query.setParameter("status", OrderStatus.ORDER)
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
