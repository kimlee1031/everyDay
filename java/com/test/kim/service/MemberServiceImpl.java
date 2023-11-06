package com.test.kim.service;// MemberServiceImpl.java
import com.test.kim.dto.Member;
import com.test.kim.repository.MemberRepository;
import com.test.kim.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void insertMember(Member member) {
        // 사용자 추가 로직을 여기에 구현
        // 예를 들어, 사용자 저장
        String memberId = member.getMemberId();
        Member byMemberId = memberRepository.findByMemberId(memberId);
        if (byMemberId != null) {
            return;
        } else {
            memberRepository.save(member);
            log.info("사용자 회원가입 성공");
        }

    }

    @Override
    public Member findMember(Long memberNumber) {
        log.info("사용자 검색 ");
        // 사용자 검색 로직을 여기에 구현
        return memberRepository.findByN(memberNumber);


    }
    @Override
    public Member findMemberByAll(String memberId, String password) {
        Member foundMember = memberRepository.findByMemberId(memberId);
            if (foundMember != null && foundMember.getMemberPwd().equals(password)) {
                    log.info("사용자 검색 완료");
                    return foundMember;
            } else {
                log.info("일치하는 사용자가 존재하지 않습니다.");
                // 일치하지 않으면 null 반환
                return null;
            }
    }
}
