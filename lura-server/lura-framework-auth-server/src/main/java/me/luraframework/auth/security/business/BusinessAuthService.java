package me.luraframework.auth.security.business;

import lombok.RequiredArgsConstructor;
import me.luraframework.auth.security.AuthUserDto;
import me.luraframework.auth.security.TokenProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessAuthService {

    private final ProviderManager providerManager;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final BusinessRepository businessRepository;

    public String login(AuthUserDto authUserDto) {

        Authentication authenticate = providerManager.authenticate(new BusinessAuthentication(authUserDto.getUsername(), authUserDto.getPassword()));

        Business business = (Business) authenticate.getPrincipal();

        BusinessJwtUser jwtUser = new BusinessJwtUser(business);
        String token = tokenProvider.createToken(jwtUser);
        return token;
    }

    public void register(AuthUserDto authUserDto) {
        businessRepository.save(new Business(authUserDto.getUsername(), passwordEncoder.encode(authUserDto.getPassword())));
    }

}
