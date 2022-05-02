package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
                                            // IDENTITY = DB가 알아서 추가해준다.
    private Long id;

    @Column // DB의 이름은 username으로 매핑된다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
