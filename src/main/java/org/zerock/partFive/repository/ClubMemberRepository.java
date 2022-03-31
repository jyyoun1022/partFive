package org.zerock.partFive.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.partFive.entity.ClubMember;

import javax.annotation.security.PermitAll;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember,String> {

    @EntityGraph(attributePaths = {"roleSet"},type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.email=:email and m.fromSocial =:social")
    Optional<ClubMember> findByEmail(@Param("email")String email, @Param("social")boolean social);

}
