package study.oauth_base.authentication.infra.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import study.oauth_base.authentication.domain.oauth.OAuthLoginParams;
import study.oauth_base.member.domain.OAuthProvider;

@Getter
@NoArgsConstructor
public class NaverLoginParams implements OAuthLoginParams {
    private String authorizationCode;
    private String state;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        body.add("state", state);
        return body;
    }
}