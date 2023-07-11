package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceTest {
	
	MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
    OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    
    @Test
    void createOrder() {
    	
    	Long memberId = 1L;
    	Member member = new Member(memberId, "memberA", Grade.VIP);
    	memberService.join(member);
    	
    	Order order = orderService.createOrder(memberId, "itemA", 10000);
    	
    	Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}
