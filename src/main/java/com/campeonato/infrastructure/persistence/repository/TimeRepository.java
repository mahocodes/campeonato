package com.campeonato.infrastructure.persistence.repository;

import com.campeonato.infrastructure.persistence.entity.TimeEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TimeRepository extends CrudRepository<TimeEntity, Long> {

}
