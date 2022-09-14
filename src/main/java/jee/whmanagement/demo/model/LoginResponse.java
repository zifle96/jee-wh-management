package jee.whmanagement.demo.model;

import jee.whmanagement.demo.entity.RefreshToken;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class LoginResponse {


    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long userId;

    public LoginResponse(String jwtToken, String refreshToken, Long userId) {
        this.token= jwtToken;
        this.refreshToken =refreshToken;
        this.userId =userId;
    }
}
