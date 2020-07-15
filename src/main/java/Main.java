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
            Query query = em
                    .createQuery("select count(m.id) from Member m, Item i " +
                            "where m.name = i.name");

            // 페이징 API
            Object resultList = query.getSingleResult();

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
