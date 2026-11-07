package com.example.codeplaxpagamentosapi.models;


import com.example.codeplaxpagamentosapi.enums.StatusCobranca;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cobrancas")
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(length = 4, nullable = false)
    private String cartaoFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCobranca status = StatusCobranca.PENDENTE;

    @Column()
    private Integer tentativasWebhook = 0;

    @Column()
    private Boolean webhook = false;


}
