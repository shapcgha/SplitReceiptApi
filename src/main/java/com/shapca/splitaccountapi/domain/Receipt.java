package com.shapca.splitaccountapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Receipt {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private long ownerId;

    @ManyToMany(mappedBy = "receipts", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<Product> products;
}
