import model.Member;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // TypedQuery
        TypedQuery typedQuery = em.createQuery("select m from Member m", Member.class);
        List<Member> resultList = typedQuery.getResultList();

        for(Member member : resultList) {
            System.out.println(member.toString());
        }

        // Query
        Query query = em.createQuery("select m.id, m.name from Member m");
        List resultList2 = query.getResultList();

        for (Object o : resultList2) {
            Object[] result = (Object[]) o; // 결과가 둘 이상이면 Object[]를 반환
            System.out.println("member.id = " + result[0]);     // JPQL에 쓰여진 순서대로 매핑
            System.out.println("member.name = " + result[1]);   // JPQL에 쓰여진 순서대로 매핑
        }

        tx.commit();

        em.close();
        emf.close();
    }
}
