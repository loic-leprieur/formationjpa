package fr.dawan.jpa.listeners;

import java.time.LocalDateTime;

import fr.dawan.jpa.entities.interceptor.BaseAuditable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.java.Log;

@Log
public class AuditListener {

    @PrePersist
    public void onPersist(BaseAuditable ba) {
        log.info("@PrePersist");
        ba.setCreated(LocalDateTime.now());
        ba.setModified(LocalDateTime.now());
    }
    
    @PreUpdate
    public void onUpdate(BaseAuditable ba) {
        log.info("@PrePersist");
        ba.setModified(LocalDateTime.now());
    }
}
