import model.DeliveryDTO;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // NEW 명령어를 이용한 객체 변환 작업 간소화
            TypedQuery<DeliveryDTO> query =
                    em.createQuery("SELECT new model.DeliveryDTO(d.id, d.status) from Delivery d", DeliveryDTO.class);

            List<DeliveryDTO> resultList = query.getResultList();

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
