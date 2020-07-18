import com.mysema.query.jpa.impl.JPAQuery;
import com.sun.org.apache.xpath.internal.operations.Or;
import model.*;
import model.Order;

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


            JPAQuery query = new JPAQuery(em);
            QMember qMember = new QMember("m"); // 생성되는 JPQL의 별칭 'm'

            List<Member> members = query.from(qMember)
                    .where(qMember.name.eq("zzzz"))
                    .orderBy(qMember.name.desc())
                    .list(qMember);

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
