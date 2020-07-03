import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class IdentityMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("learn-jpa");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    /* 비즈니스 로직 함수
    *  em : EntityManager
    * */
    public static void logic(EntityManager em) {
        // 멤버 생성

        for(int i = 0; i < 5; i ++ ) {
            Member member = new Member(); // 생성할 model.Member 엔티티 생성
            /* 생성한 model.Member 엔티티에 값을 입력
             * Main 에서와 달리 ID 값을 지정하지 않아도 @GeneratedValue 를 이용한
             * 자동 키 매핑으로 인해 값이 입력된다.
             * */
            member.setName("회원" + i);
            member.setAge(i);

            em.persist(member);
        }

        // Member 목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : members) {
            System.out.println(member.toString());
        }
    }
}
