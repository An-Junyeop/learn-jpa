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
            // 조회
            Member findMember = em.find(Member.class, 1);
            List<Product> products = findMember.getProducts();

            for(Product product : products) {
                System.out.println("product.name = " + product.getName());
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
