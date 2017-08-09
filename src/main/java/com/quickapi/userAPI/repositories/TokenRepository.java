package com.quickapi.userAPI.repositories;

import com.quickapi.userAPI.entities.TokenEntity;
import com.quickapi.userAPI.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends CrudRepository<TokenEntity, Long> {

    @Query("SELECT t FROM tokens t WHERE t.series=:series")
    public TokenEntity findByTokenSeries(@Param("series") String tokenSeries);

    @Query("SELECT t FROM tokens t WHERE t.refUser.id=:user_id")
    public TokenEntity getUserToken(@Param("user_id") Long user_id);


}
