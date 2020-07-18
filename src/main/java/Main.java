import com.sun.org.apache.xpath.internal.operations.Or;
import model.Member;
import model.Order;
import model.OrderItem;
import model.OrderStatus;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 검색조건
            Integer count = 3;
            OrderStatus status = null;
            String name = null;

            // JPQL 동적 쿼리 생성
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);

            Root<Order> o = cq.from(Order.class);
            Join<Order, OrderItem> oi = o.join("orderItems");
            Join<Order, Member> m = o.join("member");

            List<Predicate> criteria = new ArrayList<Predicate>();

            if (count != null) criteria.add(cb.ge(oi.<Number>get("count"),
                    cb.parameter(Integer.class, "count")));
            if (status != null) criteria.add(cb.equal(o.get("status"),
                    cb.parameter(OrderStatus.class, "status")));
            if (name != null) criteria.add(cb.equal(m.get("name"),
                    cb.parameter(String.class, "name")));

            cq.where(cb.and(criteria.toArray(new Predicate[0])));

            TypedQuery<Order> query = em.createQuery(cq);

            if (count != null) query.setParameter("count", count);
            if (status != null) query.setParameter("status", status);
            if (name != null) query.setParameter("name", name);

            List<Order> result = query.getResultList();

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
