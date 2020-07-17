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
            CriteriaQuery<Tuple> cq = cb.createTupleQuery();

            Root<Member> m = cq.from(Member.class);
            cq.multiselect(
                    m.get("name").alias("memberName"),
                    m.get("id").alias("memberId")
            );
            List<Tuple> result = em.createQuery(cq).getResultList();

            for (Tuple tuple : result) {
                String memberName = tuple.get("memberName", String.class);
                Long memberId = tuple.get("memberId", Long.class);
            }
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
