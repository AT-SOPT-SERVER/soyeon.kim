package org.sopt.auth.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.sopt.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("헤더가 이상합니다.");
        }
        String decodeString = authHeader.substring("Basic ".length());
        byte[] decodeByte = Base64.getDecoder().decode(decodeString);
        String credentials = new String(decodeByte, StandardCharsets.UTF_8);

        String[] parts = credentials.split(":");
        String username = parts[0];
        String password = parts[1];

        if (username.equals("soptUser") && password.equals("sopt1234")) {
            return ResponseEntity.ok("인증 성공");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
    }

    @GetMapping("/set-cookie") // 원래는 이거 받아와야 되는데 dto 대신 임시로 만들어주기
    public ResponseEntity<String> setCookie(
            HttpServletRequest request,
            HttpServletResponse response) {
        String username = "userSopt";
        String password = "sopt1234";

        // 하나의 쿠키에 여러 key-value 쌍을 넣을 수 없음
        Cookie userNameCookie = new Cookie("userId", username); // 쿠키 구워서  key, value 값 설정
        Cookie passwordCookie = new Cookie("password", password);

        userNameCookie.setPath("/"); // 쿠키 허용 범위 지정
        passwordCookie.setPath("/");

        response.addCookie(userNameCookie); // 응답에 쿠키 추가
        response.addCookie(passwordCookie);

        return ResponseEntity.ok("쿠키를 잘 구웠습니당");
    }

    // 실제로 쿠키를 가져오자!
    // 아까처럼 인코딩 디코딩 안 해도 됨!
    @GetMapping("/get-cookie")
    public ResponseEntity<String> getCookie(
            @CookieValue("userId") String userId,
            @CookieValue("password") String password
    ) {
        return ResponseEntity.ok("받은 쿠키) userId: " + userId + "\n받은 쿠키) password: " + password);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login2(HttpServletRequest request) {
        String userId = "userSopt";
        String password = "sopt1234";

        if (userId.equals("userSopt") && password.equals("sopt1234")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", new User(userId));
        }
        throw new RuntimeException("");
    }

}
