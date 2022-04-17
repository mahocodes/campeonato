package com.campeonato.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @OneToOne
    @JoinColumn(name = "id_time_mandante", referencedColumnName = "id")
    private TimeEntity timeMandante;

    @OneToOne
    @JoinColumn(name = "id_time_visitante", referencedColumnName = "id")
    private TimeEntity timeVisitante;

    @Column(name = "data_realizacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataRealizacao;

    @OneToMany(mappedBy = "partida")
    private Set<EventoEntity> eventos;

    @ManyToOne
    @JoinColumn(name = "id_torneio", nullable = false)
    private TorneioEntity torneio;
}
