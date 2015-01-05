package org.djabry.platform.service.repository;

import org.djabry.platform.persistence.jpa.entity.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by djabry on 05/01/15.
 */
@Repository
public interface UserRepository extends DBRepository<DBUser>, JpaRepository<DBUser, Long> {
}
