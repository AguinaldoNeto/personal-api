package br.com.aguinaldo.personal_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User {

    @Version
    private Long version; // Controle de concorrência

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotNull
    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotNull
    @Column(nullable = false, length = 100)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Instant createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Instant updatedAt;

    @Column()
    private Date lastLogin;

    @Column()
    private boolean active = true;

    //antes da persistência, os campos abaixo recebem a data
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    //antes da persistência, os campos abaixo recebem a data
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
