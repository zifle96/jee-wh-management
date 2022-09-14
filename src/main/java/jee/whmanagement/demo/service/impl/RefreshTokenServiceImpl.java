package jee.whmanagement.demo.service.impl;


import jee.whmanagement.demo.TokenRefreshException;
import jee.whmanagement.demo.entity.RefreshToken;
import jee.whmanagement.demo.repository.RefreshTokenRepository;
import jee.whmanagement.demo.repository.UserRepository;
import jee.whmanagement.demo.service.RefreshTokenService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

  @Value("${security.jwtRefresh.expire-length}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  @Override
  public RefreshToken createRefreshToken(Long userId)  throws ServiceException {

    RefreshToken refreshToken = new RefreshToken();
    try {
      refreshToken.setUser(userRepository.findById(userId).get());
      refreshToken.setExpiryDate(Timestamp.from(Instant.now().plusMillis(refreshTokenDurationMs)));
      refreshToken.setToken(UUID.randomUUID().toString());

      refreshToken = refreshTokenRepository.save(refreshToken);
    }catch ( ServiceException ex){
      throw new ServiceException(ex.getMessage());
    }

    return refreshToken;
  }

  @Override
  public RefreshToken verifyExpiration(RefreshToken token)  {
    if (token.getExpiryDate().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
      refreshTokenRepository.delete(token);
      //throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }
    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}
