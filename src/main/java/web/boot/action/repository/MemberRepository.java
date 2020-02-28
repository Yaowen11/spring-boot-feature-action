package web.boot.action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.boot.action.entity.Member;

/**
 * @author z
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {}
