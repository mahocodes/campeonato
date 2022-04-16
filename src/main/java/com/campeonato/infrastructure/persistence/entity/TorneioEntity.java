package com.campeonato.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_torneio", schema = "campeonato")
public class TorneioEntity implements Serializable {

    private static final long serialVersionUID = 8062484914175483822L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_inicio", columnDefinition = "DATE")
    private LocalDate dataInicio;

    @Column(name = "data_fim", columnDefinition = "DATE")
    private LocalDate dataFim;

    @OneToOne
    @JoinColumn(name = "id_campeao", referencedColumnName = "id")
    private TimeEntity campeao;

    @OneToOne
    @JoinColumn(name = "id_craque", referencedColumnName = "id")
    private JogadorEntity craque;

    @OneToMany(mappedBy = "torneio")
    private Set<PartidaEntity> partidas;
}
