package com.codemon.authserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity()
@Data
public class Usuario extends BaseEntity{
    @Column(length = 100)
    private String nombre;
    @Column(length = 100, unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 150)
    private String password;
    @Column(length = 10)
    private String telefono;
    //relations
    @Column(name = "rol_id")
    private Long rolId;
    @ManyToOne()
    @JoinColumn(name = "rol_id", insertable = false,updatable = false)
    private Rol rol;

    @PrePersist
    void prePersit(){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        this.password = bcrypt.encode(this.password);
    }
}
