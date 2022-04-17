package com.campeonato.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_evento", schema = "campeonato")
public class EventoEntity implements Serializable {

    private static final long serialVersionUID = 734647442696607663L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento")
    private TipoEvento tipoEvento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_advertencia")
    private TipoAdvertencia tipoAdvertencia;

    @Column(name = "data", columnDefinition = "TIMESTAMP")
    private LocalDateTime data;

    @Column(name = "tempo")
    private Integer tempo;

    @Column(name = "minutos")
    private Integer minutos;

    @Column(name = "minuto")
    private Integer minuto;

    @OneToOne
    @JoinColumn(name = "id_jogador", referencedColumnName = "id")
    private JogadorEntity jogador;

    @OneToOne
    @JoinColumn(name = "id_jogador_substituto", referencedColumnName = "id")
    private JogadorEntity jogadorSubstituto;

    @ManyToOne
    @JoinColumn(name = "id_partida", nullable = false)
    private PartidaEntity partida;
}
