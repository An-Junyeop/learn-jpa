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

            StoredProcedureQuery spq =
                    em.createStoredProcedureQuery("proc_multiply");
            spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);

            spq.setParameter(1, 100);
            spq.execute();

            Integer out = (Integer) spq.getOutputParameterValue(2);
            System.out.println(out);

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
