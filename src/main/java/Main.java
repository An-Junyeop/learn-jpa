import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Criteria 사용 준비
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        // 루트 클래스
        Root<Member> m = query.from(Member.class);

        // 쿼리 생성
        CriteriaQuery<Member>  cq = query.select(m).where(cb.equal(m.get("name"), "member_3"));
        List<Member> resultList = em.createQuery(cq).getResultList();

        for(Member member : resultList) {
            System.out.println(member.toString());
        }

        tx.commit();

        em.close();
        emf.close();
    }
}
