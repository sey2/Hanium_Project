package com.fishing.demo.api.service;

import com.fishing.demo.api.domain.entity.Users;
import com.fishing.demo.api.domain.repository.UsersRepository;
import com.fishing.demo.api.domain.repository.RefreshTokenRepository;
import com.fishing.demo.api.dto.request.JoinRequestDto;
import com.fishing.demo.api.dto.response.TokenResponseDto;
import com.fishing.demo.exception.CustomException;
import com.fishing.demo.exception.ErrorCode;
import com.fishing.demo.utils.JwtTokenUtil;
import com.fishing.demo.api.domain.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.secret}")
    private String key;

    @Autowired
    public UsersService(UsersRepository usersRepository,
            RefreshTokenRepository refreshTokenRepository,
            BCryptPasswordEncoder encoder,
            JwtTokenUtil jwtTokenUtil) {
        this.usersRepository = usersRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public void checkDuplicate(String userId) {
        if (usersRepository.findByUserId(userId).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_USERID);
        }
    }

    public String join(JoinRequestDto joinDto) {
        Users user = Users.builder()
                .userId(joinDto.getUserId())
                .password(encoder.encode(joinDto.getPassword()))
                .nickName(joinDto.getNickName())
                .profileImagePath(joinDto.getProfileImageUrl())
                .build();

        usersRepository.save(user);
        return joinDto.getUserId() + "님이 성공적으로 회원가입되었습니다.";
    }

    public TokenResponseDto login(String userId, String password) {
        Users user = usersRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));

        if (!encoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        TokenResponseDto token = JwtTokenUtil.createAllToken(String.valueOf(user.getUserId()), key);

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserId(userId);

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(token.getRefreshToken()));
        } else {
            RefreshToken newRefreshToken = new RefreshToken(userId, token.getRefreshToken());
            refreshTokenRepository.save(newRefreshToken);
        }

        return TokenResponseDto.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    // 나중에 Redis로 저장하는거 구현하기
    public TokenResponseDto reissue(String refreshToken) {
        refreshToken = refreshToken.split(" ")[1];

        if (jwtTokenUtil.isExpired(refreshToken, key)) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }

        RefreshToken inputToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REFRESH_TOKEN));

        RefreshToken token = refreshTokenRepository.findByUserId(inputToken.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REFRESH_TOKEN));

        TokenResponseDto newToken = JwtTokenUtil.createAllToken(inputToken.getUserId(), key);
        refreshTokenRepository.save(token.updateToken(newToken.getRefreshToken()));

        return TokenResponseDto.builder()
                .accessToken(newToken.getAccessToken())
                .refreshToken(newToken.getRefreshToken())
                .build();
    }

    Optional<Users> findByUsername(String username);
}
