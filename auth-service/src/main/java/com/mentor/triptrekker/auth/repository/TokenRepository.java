package com.mentor.triptrekker.auth.repository;

import java.util.List;
import java.util.Optional;

import com.mentor.triptrekker.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value =
            "select t " +
            "from Token t " +
                    "inner join User u " +
                    "on t.user.id = u.id " +
            "where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(@Param("id") Integer id);

    Optional<Token> findByToken(String token);
}
