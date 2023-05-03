package com.shapca.splitaccountapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    Set<User> owners;
    @NotNull
    @PositiveOrZero
    long price;

    @NotNull
    @PositiveOrZero
    long count;

    @NotNull
    @NotEmpty
    String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    Receipt receipt;

    @NotNull
    boolean isOwn;
}
