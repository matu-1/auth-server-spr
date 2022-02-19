package com.codemon.authserver.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    protected Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Date updatedAt;
}
