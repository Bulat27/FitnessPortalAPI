package rs.ac.bg.fon.njt.fitnessportal.repositories;

import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.fitnessportal.entities.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends UserBaseRepository<Member> {
    Member findByVerificationCode(String code);
}
