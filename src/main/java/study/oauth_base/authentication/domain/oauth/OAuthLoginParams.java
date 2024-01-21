package study.oauth_base.authentication.domain.oauth;

import org.springframework.util.MultiValueMap;
import study.oauth_base.member.domain.OAuthProvider;

public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
