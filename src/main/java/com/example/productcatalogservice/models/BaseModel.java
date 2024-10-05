package com.example.productcatalogservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    @Enumerated(EnumType.ORDINAL)
    private State state;
}
