package jee.whmanagement.demo.resource;


import jee.whmanagement.demo.TokenRefreshException;
import jee.whmanagement.demo.config.JwtTokenUtil;
import jee.whmanagement.demo.entity.RefreshToken;
import jee.whmanagement.demo.model.LoginRequest;
import jee.whmanagement.demo.model.LoginResponse;
import jee.whmanagement.demo.model.TokenRefreshRequest;
import jee.whmanagement.demo.model.TokenRefreshResponse;
import jee.whmanagement.demo.service.RefreshTokenService;
import jee.whmanagement.demo.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@ResponseBody
public class AuthenticationResource {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String jwtToken =jwtTokenUtil.createToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId());
        return new LoginResponse(jwtToken, refreshToken.getToken(),userDetails.getUserId());

    }

    @PostMapping("/refresh-token")
    @ResponseBody
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) throws TokenRefreshException {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService:: verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    UserDetailsImpl userDetails =UserDetailsImpl.build(user);
                    String token = jwtTokenUtil.createToken(userDetails);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<String> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUserId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("Log out successful!");
    }
}
