/*
 * Copyright (c) 2015 Daniel Jabry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
