package com.github.djabry.platform.persistence.jpa.entity;

import com.github.djabry.platform.domain.api.DomainObject;
import com.github.djabry.platform.domain.api.User;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by djabry on 06/01/15.
 */

@Data
@MappedSuperclass
public abstract class AbstractAuditable<U extends User, ID extends Serializable> implements DomainObject<U, ID> {

    private static final long serialVersionUID = 141481953116476081L;
    @ManyToOne(cascade = CascadeType.ALL)
    private U createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private U lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public U getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedDate() {
        return null == this.createdDate ? null : new DateTime(this.createdDate);
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = null == createdDate ? null : createdDate.toDate();
    }

    public U getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public DateTime getLastModifiedDate() {
        return null == this.lastModifiedDate ? null : new DateTime(this.lastModifiedDate);
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
    }
}
