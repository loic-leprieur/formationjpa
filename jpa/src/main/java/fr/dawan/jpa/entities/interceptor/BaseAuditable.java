package fr.dawan.jpa.entities.interceptor;

import java.time.LocalDateTime;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import fr.dawan.jpa.listeners.AuditListenner;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

@EntityListeners(AuditListenner.class)
@MappedSuperclass
public abstract class BaseAuditable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(updatable = false,nullable=false)
    private LocalDateTime created;

    @Column(nullable=false)
    private LocalDateTime modified;

}
