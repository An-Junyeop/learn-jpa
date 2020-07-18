import com.mysema.query.jpa.impl.JPAQuery;
import model.Member;
import static model.QMember.member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();


            JPAQuery query = new JPAQuery(em);

            List<Member> members = query.from(member)
                    .where(member.name.eq("zzzz"))
                    .orderBy(member.name.desc())
                    .list(member);

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
