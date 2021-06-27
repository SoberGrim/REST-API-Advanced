package com.epam.esm.dto.audit;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * The type Audit listener.
 */
@Component
@Log4j2
public class AuditListener {
    @PrePersist
    private void onPrePersist(Object object) {
        log.log(Level.INFO, "persist object: " + object);
    }

    @PreUpdate
    private void onPreUpdate(Object object) {
        log.log(Level.INFO, "update object: " + object);
    }

    /**
     * On pre remove.
     *
     * @param object the object
     */
    @PreRemove
    public void onPreRemove(Object object) {
        log.log(Level.INFO, "remove object: " + object);
    }
}
