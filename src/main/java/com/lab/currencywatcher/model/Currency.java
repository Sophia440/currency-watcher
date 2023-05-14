package com.lab.currencywatcher.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currency")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Currency {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;

    private String symbol;

    private String name;

    @Column(name = "price_usd")
    private BigDecimal priceUsd;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "currency", orphanRemoval = true)
    @Builder.Default
    private List<UserRecord> userRecords = new ArrayList<>();

    @Column(name = "version")
    @Version
    private long version;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modified = LocalDateTime.now();
    }

}