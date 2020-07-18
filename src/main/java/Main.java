import com.sun.org.apache.xpath.internal.operations.Or;
import model.Member;
import model.Order;
import model.OrderItem;
import model.OrderStatus;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
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
            OrderStatus status = OrderStatus.CANCEL;
            String name = "test";

            // JPQL 동적 쿼리 생성
            StringBuilder jpql = new StringBuilder("select o from Order o join o.orderItems oi join o.member m");
            List<String> criteria = new ArrayList<String>();

            if (count != null) criteria.add(" oi.count > :count");
            if (status != null) criteria.add(" o.status = :status");
            if (name != null) criteria.add(" m.name = :name");

            if(criteria.size() > 0) jpql.append(" where ");

            for (int i = 0; i < criteria.size(); i ++) {
                if (i > 0) jpql.append(" and ");
                jpql.append(criteria.get(i));
            }

            TypedQuery<Order> query = em.createQuery(jpql.toString(), Order.class);

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
