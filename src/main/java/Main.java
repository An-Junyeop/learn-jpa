import model.Member;
import model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learn-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

/*
            // 상품 등록
            Product productA = new Product();
            productA.setName("ProductA");
            em.persist(productA);

            // 회원 등록 및 회원과 상품 등록
            Member member1 = new Member();
            member1.setName("Member1");
            member1.getProducts().add(productA);
            em.persist(member1);

*/
/*

            // 회원을 통한 조회
            Member findMember = em.find(Member.class, 1);
            List<Product> products = findMember.getProducts();

            for(Product product : products) {
                System.out.println("product.name = " + product.getName());
            }
*/

/*

            // 상품을 통한 회원 조회
            Product findProduct = em.find(Product.class, 1);
            List<Member> members = findProduct.getMembers();

            // 새로운 회원 생성
            Member member2 = new Member();
            member2.setName("Member2");
            em.persist(member2);

            // 회원의 상품 목록에 조회한 상품 등록
            member2.addProduct(findProduct);

            for(Member member : members) {
                System.out.println("Member.name = " + member.getName());
            }
*/

/*

            Member findMember = em.find(Member.class, 2);

            // 새로운 상품 2, 3, 4
            for(int i = 2; i < 5; i ++) {
                Product product = new Product();
                product.setName("Product" + i);
                em.persist(product);

                // 새로운 상품을 회원에 등록
                findMember.addProduct(product);
            }
*/

            // 상품 조회
            Product findProduct = em.find(Product.class, 1);

            // 회원 생성
            Member member = new Member();
            member.setName("Member3");
            em.persist(member);

            // 상품에 회원 추가 (편의성 메소드 처리가 안되어 있어 안됌)
            findProduct.getMembers().add(member);


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
