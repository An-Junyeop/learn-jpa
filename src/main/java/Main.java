import model.Member;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // getSingleResult
            Member member = em.createQuery("select m from Member m where m.id = :id", Member.class)
                    .setParameter("id", 13L)
                    .getSingleResult();
            System.out.println(member.toString());

            // getSingleResult - 검색 결과가 없는 경우
//            Member member2 = em.createQuery("select m from Member m where m.id = :id", Member.class)
//                    .setParameter("id", 1L)
//                    .getSingleResult();
//            System.out.println(member.toString());

            // getSingleResult - 검색 결과가 한개 초과인 경우
            Member member3 = em.createQuery("select m from Member m", Member.class)
                    .getSingleResult();
            System.out.println(member.toString());



            tx.commit();
        } catch (NoResultException e) {
            System.out.println("검색 결과가 없을 경우");
            tx.rollback();
        } catch (NonUniqueResultException e) {
            System.out.println("검색 결과가 한개 초과인 경우");
        } finally {
            em.close();
        }

        emf.close();
    }
}
