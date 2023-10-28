package umcHomework.umc1.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umcHomework.umc1.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
