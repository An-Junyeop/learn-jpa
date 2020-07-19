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

            String sql =
                    "SELECT M.*, I.ORDER_COUNT " +
                            "FROM MEMBER M " +
                            "LEFT JOIN " +
                            "   (SELECT MEMBER_ID, COUNT(*) AS ORDER_COUNT " +
                            "   FROM ORDERS " +
                            "   GROUP BY MEMBER_ID " +
                            "   ) I " +
                            "ON M.MEMBER_ID = I.MEMBER_ID";

            Query nativeQuery = em.createNativeQuery(sql, "memberWithOrderCount");

            List<Object[]> result = nativeQuery.getResultList();

            for(Object[] row : result) {
                Member member = (Member) row[0];
                BigInteger orderCount = (BigInteger) row[1];

                System.out.println(member.getId() + ", " + member.getName());
                System.out.println(orderCount);
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
