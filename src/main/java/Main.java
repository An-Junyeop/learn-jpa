import model.DeliveryDTO;
import model.Member;

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
            TypedQuery<Member> query = em
                    .createQuery("SELECT m FROM Member m LEFT OUTER JOIN m.orders", Member.class);

            // 페이징 API
            query.setFirstResult(10); // 시작 11부터
            query.setMaxResults(20); // 20개의 데이터 ex. 11~30
            List<Member> resultList = query
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
