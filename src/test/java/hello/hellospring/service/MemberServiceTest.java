package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given 뭔가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        // when 이걸 실행했을 때
        long saveId = memberService.join(member);

        // then 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }*/


        // then
    }

    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        memberRepository.save(member2);



        // when
        List<Member> result = memberService.findMembers();

        // then

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member result = memberService.findOne(1L).get();

        System.out.println(result.getName());

        assertThat(member1.getName()).isEqualTo(result.getName());
    }
}