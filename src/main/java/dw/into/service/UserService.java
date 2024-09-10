package dw.into.service;

import dw.into.dto.UserDto;
import dw.into.model.Authority;
import dw.into.model.User;
import dw.into.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserById(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    public List<User> getUsersAll() {
        return userRepository.findAll();
    }

    public String saveUser(UserDto userDto) {
        Optional<User> userById = userRepository.findByUserId(userDto.getUserId());
        Optional<User> userByNickname = userRepository.findByNickname(userDto.getNickname());
        if (userById.isPresent() || userByNickname.isPresent()) {
            return "0"; // userId 또는 nickname 중 하나라도 중복되는 경우
        } else {
            Authority authority = new Authority();
            authority.setAuthorityName("ROLE_USER");
            User user = new User(
                    userDto.getUserId(),
                    userDto.getName(),
                    bCryptPasswordEncoder.encode(userDto.getPassword()),
                    userDto.getBirthDate(),
                    userDto.getPhoneNumber(),
                    userDto.getAddress(),
                    userDto.getGender(),
                    userDto.getEmail(),
                    userDto.getNickname(),
                    authority);
            return userRepository.save(user).getUserId();
        }
    }
    public User getUserByUserId(String userID) {
        return userRepository.findByUserId(userID)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userID));
    }
    public User getUserFromToken(String token) {
        try {
            // JWT 시크릿 키 생성
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            // JWT 토큰 파싱
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 토큰에서 사용자 ID 추출
            String userId = claims.getSubject();

            // 데이터베이스에서 사용자 정보 조회
            return userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

}
