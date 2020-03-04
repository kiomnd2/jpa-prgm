package hellpjpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //설정정보를 가져옴


        EntityManager em = emf.createEntityManager();
        //JPA에서의 모든 작업은 트랜젝션 안에서 작동해야 함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{


            tx.commit();
        } catch(Exception e)
        {
            tx.rollback();
        }
        finally {
            em.close(); //내부적으로 데이터 커넥션을 물고있다
        }
        emf.close();
    }
}
