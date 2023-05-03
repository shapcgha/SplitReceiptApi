package com.shapca.splitaccountapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 16)
    @Pattern(regexp = "[a-z]+", message = "Only small latin letters")
    private String login;
    @JsonIgnore
    private String passwordSha;
    @ManyToMany()
    @JoinTable()
    @JsonIgnore
    private Set<Receipt> receipts;

    @ManyToMany(mappedBy = "owners")
    @JsonIgnore
    private List<Product> products;

    public void setPasswordSha(String passwordSha) {
        this.passwordSha = passwordSha;
    }
}
