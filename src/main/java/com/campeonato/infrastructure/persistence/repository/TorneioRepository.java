package com.campeonato.infrastructure.persistence.repository;

import com.campeonato.infrastructure.persistence.entity.TorneioEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TorneioRepository extends CrudRepository<TorneioEntity, Long> {

}
