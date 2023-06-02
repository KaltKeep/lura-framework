package me.luraframework.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final TokenProvider tokenProvider;
    private final OnlineService onlineService;

    @PostMapping("/v1/userInfoByToken")
    public JwtUser getUserInfoByToken(String accessToken) {
        return tokenProvider.checkToken(accessToken);
    }
}
