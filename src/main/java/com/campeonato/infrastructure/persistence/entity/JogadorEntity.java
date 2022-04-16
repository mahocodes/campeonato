package com.campeonato.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_jogador", schema = "campeonato")
public class JogadorEntity implements Serializable {

    private static final long serialVersionUID = -4221013071587023527L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento", columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "id_time", referencedColumnName = "id")
    private TimeEntity time;

}