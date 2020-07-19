import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.StringPath;
import model.Item;
import model.Member;
import model.QItem;
import org.h2.util.StringUtils;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<Object[]> result = em
                    .createNamedQuery("Member.memberWithOrderCount")
                    .getResultList();

            for (Object[] row : result) {
                Member member = (Member) row[0];
                BigInteger orderCount = (BigInteger) row[1];

                System.out.println(member.getName() + ", " + orderCount);
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
