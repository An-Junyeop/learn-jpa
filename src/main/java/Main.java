import com.mysema.query.SearchResults;
import com.mysema.query.jpa.impl.JPAQuery;
import model.Member;
import static model.QMember.member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();


            JPAQuery query = new JPAQuery(em);

            int page = 3;
            int limit = 5;

            SearchResults<Member> result = query.from(member)
                    .offset((page - 1) * limit).limit(limit)
                    .listResults(member);

            long totalCount = result.getTotal();
            long resultLimit = result.getLimit();
            long offset = result.getOffset();
            List<Member> list = result.getResults();

            System.out.println("total count: " + totalCount);
            System.out.println("offset: " + offset);
            System.out.println("limit: " + resultLimit);

            for (Member m : list) {
                System.out.println(m.getId());
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
