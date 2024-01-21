package study.oauth_base.authentication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.oauth_base.authentication.domain.AuthTokens;
import study.oauth_base.authentication.domain.AuthTokensGenerator;
import study.oauth_base.authentication.domain.oauth.OAuthInfoResponse;
import study.oauth_base.authentication.domain.oauth.OAuthLoginParams;
import study.oauth_base.authentication.domain.oauth.RequestOAuthInfoService;
import study.oauth_base.member.domain.Member;
import study.oauth_base.member.domain.MemberRepository;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}