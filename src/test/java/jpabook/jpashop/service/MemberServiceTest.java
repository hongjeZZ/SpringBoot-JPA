package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;

    @Test
    public void 회원가입() {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = service.join(member);

        // then
        assertThat(member).isEqualTo(repository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member0 = new Member();
        member0.setName("kim");
        Member member1 = new Member();
        member1.setName("kim");

        // when
        service.join(member0);

        // then
        assertThatThrownBy(() -> service.join(member1)).isInstanceOf(IllegalStateException.class);
    }
}