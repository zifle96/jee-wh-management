package jee.whmanagement.demo.repository;


import jee.whmanagement.demo.entity.RefreshToken;
import jee.whmanagement.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByToken(String token);
  @Modifying
  int deleteByUser(User user);
}
