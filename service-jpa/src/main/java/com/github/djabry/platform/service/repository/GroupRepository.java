package com.github.djabry.platform.service.repository;

import com.github.djabry.platform.persistence.jpa.entity.DBGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by djabry on 05/01/15.
 */
@Repository
public interface GroupRepository extends DBRepository<DBGroup>, JpaRepository<DBGroup, Long> {
}
