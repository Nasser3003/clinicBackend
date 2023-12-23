package com.almoatasem.demo.repository.userRepos;

import com.almoatasem.demo.models.entitiy.user.AbstractUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AbstractUserEntity, Long> {
    Optional<AbstractUserEntity> findById(Long aLong);
    Optional<AbstractUserEntity> findByEmail(String aEmail);
    List<AbstractUserEntity> findAllById(Iterable<Long> longs);

}