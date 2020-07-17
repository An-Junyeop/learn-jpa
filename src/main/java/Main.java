import com.sun.org.apache.bcel.internal.generic.LSTORE;
import model.Delivery;
import model.DeliveryDTO;
import model.Member;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

            CriteriaQuery<DeliveryDTO> cq = cb.createQuery(DeliveryDTO.class);

            Root<Delivery> d = cq.from(Delivery.class);

            cq.select(cb.construct(DeliveryDTO.class, d.get("id"), d.get("status")));

            List<DeliveryDTO> result = em.createQuery(cq).getResultList();

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
