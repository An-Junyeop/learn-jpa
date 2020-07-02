import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        * 엔티티 매니터 팩토리 생성
        * persistence.xml 설정에서 learn-jpa 로 설정했으므로 learn-jpa 입력
        * */
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("learn-jpa");

        EntityManager em = emf.createEntityManager(); // 엔티티 매니터 팩토리를 이용하여 생성


        EntityTransaction tx = em.getTransaction(); // 엔티티 매니저로부터 트랜잭션을 받음


        try {
            tx.begin(); // 트랜잭션 시작
            logic(em); // 비즈니스 로직
            tx.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 예외 발생 시 롤백
        } finally {
            em.close(); // 엔티티 매니터 종료
        }

        emf.close(); // 엔티티 매니터 팩토리 종료
    }

    /* 비즈니스 로직 함수
    *  em : EntityManager
    * */
    public static void logic(EntityManager em) {
        // 멤버 생성
        String id = "id1"; // 멤버 ID 지정
        Member member = new Member(); // 생성할 model.Member 객체 생성
        // 생성한 model.Member 객체에 값을 입력
        member.setId(id);
        member.setName("AnJunyeop");
        member.setAge(25);

        // 데이터베이스에 저장
        em.persist(member);

        // 수정을 위한 객체의 값 수정
        member.setAge(24);

        // id1 이란 ID 값으로 조회
        Member findMember = em.find(Member.class, id);
        System.out.println(findMember.toString());

        // 회원 테이블 목록 조회
        List<Member> members = em
                .createQuery("select m from Member m", Member.class)
                .getResultList();
        System.out.println("members.size = " + members.size());

        // 데이터베이스에서 삭제
        em.remove(member);
    }
}
