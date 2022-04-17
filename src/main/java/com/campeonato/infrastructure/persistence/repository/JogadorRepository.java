package com.campeonato.infrastructure.persistence.repository;

import com.campeonato.infrastructure.persistence.entity.JogadorEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface JogadorRepository extends CrudRepository<JogadorEntity, Long> {


}
