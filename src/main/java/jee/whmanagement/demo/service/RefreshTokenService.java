package jee.whmanagement.demo.service;


import jee.whmanagement.demo.entity.RefreshToken;
import org.hibernate.service.spi.ServiceException;

import java.util.Optional;


public interface RefreshTokenService {

    RefreshToken verifyExpiration(RefreshToken token)throws ServiceException;
    Optional<RefreshToken> findByToken(String token) throws ServiceException;
    int deleteByUserId(Long userId) throws ServiceException;
    RefreshToken createRefreshToken(Long userId) throws ServiceException;


}
