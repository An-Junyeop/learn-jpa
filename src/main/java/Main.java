import model.DeliveryDTO;
import model.Member;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TypedQuery<Member> query = em.createQuery("SELECT m from Member m ORDER BY m.id DESC", Member.class);

            // 페이징 API
            query.setFirstResult(10);
            query.setMaxResults(20);
            List<Member> resultList = query.getResultList();

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
