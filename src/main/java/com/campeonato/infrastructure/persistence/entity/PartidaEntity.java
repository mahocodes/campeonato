package com.campeonato.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_partida", schema = "campeonato")
public class PartidaEntity implements Serializable {

    private static final long serialVersionUID = -1166361402614297307L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "partida")
    private Set<EventoEntity> eventos;

    @ManyToOne
    @JoinColumn(name = "id_torneio", nullable = false)
    private TorneioEntity torneio;
}
