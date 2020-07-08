import model.Child;
import model.Parent;
import model.ParentId;

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

        tx.begin();

        saveParent(em);
        Parent parent = findParent(em);

        saveChild(em, parent);
        List<Child> childs = findChild(em, parent);

        for(Child c : childs) {
            System.out.println("child.id = " + c.getId());
        }

        tx.commit();
        em.close();
        emf.close();
    }

    private static List<Child> findChild(EntityManager em, Parent parent) {
        return em
                .createQuery("select c from Child c where c.parent=:parent")
                .setParameter("parent", parent)
                .getResultList();
    }

    private static void saveChild(EntityManager em, Parent parent) {
        Child child = new Child();
        child.setParent(parent);
        em.persist(child);

        System.out.println(child);
    }

    private static Parent findParent(EntityManager em) {
        ParentId parentId = new ParentId(1, 1);
        Parent findParent = em.find(Parent.class, parentId);

        System.out.println("Find parent.name = " + findParent.getName());

        return findParent;
    }

    private static void saveParent(EntityManager em) {
        Parent parent = new Parent();
        parent.setId(new ParentId(1, 1));
        parent.setName("parent1");
        em.persist(parent);
    }


}