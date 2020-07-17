import com.sun.org.apache.xpath.internal.operations.Or;
import model.Member;
import model.Order;
import model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> o = query.from(Order.class);

            Subquery<Member> subQuery = query.subquery(Member.class);
            Root<Order> subO = subQuery.correlate(o); // 메인 쿼리의 별칭을 가져옴
            Join<Order, Member> m = subO.join("member");
            subQuery.select(m)
                    .where(cb.equal(m.get("name"), "zzzz"));

            query.select(o)
                    .where(cb.exists(subQuery));

            List<Order> result = em.createQuery(query).getResultList();

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
