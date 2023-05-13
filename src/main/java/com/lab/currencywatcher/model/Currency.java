package com.lab.currencywatcher.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "currency")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Currency extends AuditableEntity<Integer> {

    @Column(name = "currency_id")
    private Integer currencyId;

    private String symbol;

    private String name;

    @Column(name = "price_usd")
    private BigDecimal priceUsd;

}