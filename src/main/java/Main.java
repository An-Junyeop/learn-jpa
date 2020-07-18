import com.sun.org.apache.xpath.internal.operations.Or;
import model.Member;
import model.Order;
import model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
            Root<Member> m = query.from(Member.class);

            query.multiselect(m.get("id"),
                    cb.selectCase()
                            .when(cb.equal(m.get("address").get("city"), "서울"), "기본 배송")
                            .when(cb.notEqual(m.get("address").get("city"), "서울"), "특수 배송")
                            .otherwise("해외 배송")
            );

            List<Object[]> result = em.createQuery(query).getResultList();

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
