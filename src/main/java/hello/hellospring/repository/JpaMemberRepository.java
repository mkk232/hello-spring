package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
            // JPA를 사용하려면 EntityManager를 주입 받아야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // insert와 id까지 다 set해준다.
        em.persist(member);
        return member;
    }

    @Override
    // pk로 조회할 때
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
                                        // m으로 해주면 모두 매핑이 되어 값이 들어감.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
