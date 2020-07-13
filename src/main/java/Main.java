import model.Address;
import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

//            save(em);
            Member findMember = find(em, 2L);
            update(findMember);
//            remove(em, findMember);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();

        }
        emf.close();
    }

    private static void update(Member member) {
        // 단순 Member 엔티티 수정
        member.setHomeAddress(new Address("서울", "조원로 22길", "35, 305호"));

        /*
        * 값 타입 컬렉션에 보관된 값 타입들은 별도의 테이블에 보관
        * 때문에 여기 보관된 값 타입의 값이 변경되면 데이터베이스의
        * 원본 데이터를 찾기 힘듬
        *
        * 때문에 값 타입 컬렉션에 변경 사항이 생길 경우 기존의 값들은
        * 모두 삭제하고, 새로운 데이터를 입력
        *
        * ex) 회원번호 100번의 조소 값 타입 컬렉션이 변경될 경우
        * 100번과 연관 된 모든 데이터를 삭제하고 새로 저장
        * */
        Set<String> favoriteFoods = member.getFavoriteFoods();
        favoriteFoods.remove("피자");
        favoriteFoods.add("치킨");

        List<Address> addressHistory = member.getAddressHistory();
        addressHistory.remove(new Address("이천", "관고재로 8번길","4-4"));
        addressHistory.add(new Address("이천", "관고재로 8번길","4-4, 2층"));
    }

    private static void remove(EntityManager em, Member member) {
        /*영속성 전이 + 고아 객체 제거 기능을 필수로 가짐
        * member만 삭제해도 favoriteFoods와
        * addressHistory 모두 삭제
        * */
        em.remove(member);
    }

    private static Member find(EntityManager em, long id) {
        Member member = em.find(Member.class, id);

        for (Address address : member.getAddressHistory()) {
            System.out.println(address.getCity() + ", " + address.getStreet() + ", "
                + address.getZipcode());
        }

        for (String food : member.getFavoriteFoods()) {
            System.out.println("Favorite food : " + food);
        }
        return member;
    }

    private static void save(EntityManager em) {
        // 회원 생성
        Member member = new Member();
        member.setHomeAddress(new Address("서울", "조원로 22번길", "35"));

        // 좋아하는 음식 Set
        member.getFavoriteFoods().add("피자");
        member.getFavoriteFoods().add("감자탕");
        member.getFavoriteFoods().add("닭도리탕");

        // 주소 이력
        member.getAddressHistory().add(new Address("이천", "관고재로 8번길", "4-4"));

        em.persist(member);
    }
}
