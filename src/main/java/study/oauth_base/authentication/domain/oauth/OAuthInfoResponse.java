package study.oauth_base.authentication.domain.oauth;

import study.oauth_base.member.domain.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}