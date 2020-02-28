package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.boot.action.config.Algorithm;
import web.boot.action.entity.Member;
import web.boot.action.repository.MemberRepository;
import web.boot.action.service.HashString;
import web.boot.action.service.RedisService;
import java.security.NoSuchAlgorithmException;

/**
 * @author z
 */
@RestController
public class MemberController {
    @Autowired
    private RedisService<String, Member> redisService;
    @Autowired
    private MemberRepository repository;

    @PostMapping("/members/member")
    public Member store(@RequestParam String name, @RequestParam String password) throws NoSuchAlgorithmException {
        HashString hashCode = new HashString();
        Member member = new Member();
        member.setName(name);
        member.setPassword(hashCode.StringHashHex(password, Algorithm.SHA256));
        Member memberStore = repository.save(member);
        redisService.set(hashCode.StringHashHex(String.valueOf(memberStore.getId()), Algorithm.SHA256), member, Member.class);
        return memberStore;
    }

    @GetMapping("/members/{id}")
    public Object show(@PathVariable int id) throws NoSuchAlgorithmException {
        HashString hashString = new HashString();
        Member member = redisService.get(hashString.StringHashHex(String.valueOf(id), Algorithm.SHA256));
        if (member == null) {
            member = repository.getOne(id);
            redisService.set(hashString.StringHashHex(String.valueOf(id), Algorithm.SHA256), member, Member.class);
        }
        return member;
    }
}
