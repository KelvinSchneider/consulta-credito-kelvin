package com.kelvin.consulta_credito.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_credito", length = 50, nullable = false)
    private String numeroCredito;

    @Column(name = "numero_nfse", length = 50, nullable = false)
    private String numeroNfse;

    @Column(name = "data_constituicao", nullable = false)
    private LocalDate dataConstituicao;

    @Column(name = "valor_issqn", precision = 15, scale = 2, nullable = false)
    private BigDecimal valorIssqn;

    @Column(name = "tipo_credito", length = 50, nullable = false)
    private String tipoCredito;

    @Column(name = "simples_nacional", nullable = false)
    private boolean simplesNacional;

    @Column(name = "aliquota", precision = 5, scale = 2, nullable = false)
    private BigDecimal aliquota;

    @Column(name = "valor_faturado", precision = 15, scale = 2, nullable = false)
    private BigDecimal valorFaturado;

    @Column(name = "valor_deducao", precision = 15, scale = 2, nullable = false)
    private BigDecimal valorDeducao;

    @Column(name = "base_calculo", precision = 15, scale = 2, nullable = false)
    private BigDecimal baseCalculo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCredito() { return numeroCredito; }
    public void setNumeroCredito(String numeroCredito) { this.numeroCredito = numeroCredito; }

    public String getNumeroNfse() { return numeroNfse; }
    public void setNumeroNfse(String numeroNfse) { this.numeroNfse = numeroNfse; }

    public LocalDate getDataConstituicao() { return dataConstituicao; }
    public void setDataConstituicao(LocalDate dataConstituicao) { this.dataConstituicao = dataConstituicao; }

    public BigDecimal getValorIssqn() { return valorIssqn; }
    public void setValorIssqn(BigDecimal valorIssqn) { this.valorIssqn = valorIssqn; }

    public String getTipoCredito() { return tipoCredito; }
    public void setTipoCredito(String tipoCredito) { this.tipoCredito = tipoCredito; }

    public boolean isSimplesNacional() { return simplesNacional; }
    public void setSimplesNacional(boolean simplesNacional) { this.simplesNacional = simplesNacional; }

    public BigDecimal getAliquota() { return aliquota; }
    public void setAliquota(BigDecimal aliquota) { this.aliquota = aliquota; }

    public BigDecimal getValorFaturado() { return valorFaturado; }
    public void setValorFaturado(BigDecimal valorFaturado) { this.valorFaturado = valorFaturado; }

    public BigDecimal getValorDeducao() { return valorDeducao; }
    public void setValorDeducao(BigDecimal valorDeducao) { this.valorDeducao = valorDeducao; }

    public BigDecimal getBaseCalculo() { return baseCalculo; }
    public void setBaseCalculo(BigDecimal baseCalculo) { this.baseCalculo = baseCalculo; }
}

