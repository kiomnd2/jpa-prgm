package hellpjpa;

import javax.persistence.*;
import java.util.Date;

@Entity // name 속성 : 사용할 엔티티의 이름을 지정, 기본값은 클래스 이름을 그대로 사용
//@SequenceGenerator(name ="member_seq_generator", initialValue = 1, allocationSize = 2000)
@Table( name = "Member") //테이블 이름 명시
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id에 값을 넣으면 안됨
    private Long id;

    @Column(name ="name")
    private String username;

    //엔티티 정의시 기본 생성자가 필요함
    public Member() {
    }

    public Member( String username) {
        this.username = username;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

}
