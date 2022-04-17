package com.campeonato.infrastructure.persistence.repository;

import com.campeonato.infrastructure.persistence.entity.EventoEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface EventoRepository extends CrudRepository<EventoEntity, Long> {

}
