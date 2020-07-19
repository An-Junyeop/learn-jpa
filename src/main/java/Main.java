import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.StringPath;
import model.Item;
import model.Member;
import model.QItem;
import org.h2.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            String sql = "SELECT MEMBER_ID, NAME " +
                    "FROM MEMBER WHERE NAME = ?";


            Query query = em.createNativeQuery(sql)
                    .setParameter(1, "zzzz"); // 위치기반의 파라미터만 지원

            List<Object[]> result = query.getResultList(); // 영속성 컨텍스트에서 관리하지 않음

            System.out.println(result.get(0)[0] + ", " + result.get(0)[1]);

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
