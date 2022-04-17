package com.campeonato.infrastructure.persistence.repository;

import com.campeonato.infrastructure.persistence.entity.PartidaEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PartidaRepository extends CrudRepository<PartidaEntity, Long> {

}
