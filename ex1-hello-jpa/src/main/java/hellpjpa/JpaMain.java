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
   /*         Member member = new Member();
            member.setId(2L);
            member.setName("hellob");
            em.persist(member);*/

            ///* update
            //        hellpjpa.Member */ update
            //            Member
            //        set
            //            name=?
            //        where
            //            id=?
            Member findMember = em.find(Member.class, 1L); // 1L에 대한 멤버를 찾아 객체로 반환
            findMember.setName("hellom"); //컬랙션을 사용한것처럼 식제 객체를 찾아 값을 입력..
            em.persist(findMember);

            //JPQL 쿼리를 입력 할 수 있다
            //가장 단순한 조회방법
            List<Member> findMemebers = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0) // 페이징 시 유용 ..
                    .setMaxResults(2)
                .getResultList();

            for(Member member : findMemebers) {
                System.out.println(member.getName());
            }

            /*//커밋 이후 모두 실행됨..
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "A");
*/

            Member member = em.find(Member.class , 150L);
            member.setName("AAAAA"); //영속 상태.. 변경내용 추적

            em.detach(member); // 준영속 상태. JPA에서 해당 엔티티를 관리하지 않음
            //쿼리 실행시 , 조회쿼리는 실행되도 update 쿼리는 실행하지 않음(setName)
//            em.clear(); 영속성 컨텍스트를 초기화 함
//            em.close(); //영속성 컨텍스트를 종료..

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
