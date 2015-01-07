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

package org.djabry.platform.persistence.jpa.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.djabry.platform.domain.api.Role;
import org.djabry.platform.domain.api.UserAccount;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by djabry on 03/01/15.
 */


@Data
@Entity
//@RegisteredEntity
@EqualsAndHashCode(callSuper = true)
//@AttributeOverride(name="id",column = @Column(length=32))
public class DBUserAccount extends DBObject<String> implements UserAccount<DBUser, DBGroup> {

    @Id
    private String id = UUID.randomUUID().toString();

    
    /**
     * The user associated with the security config
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DBUser user;

    /**
     *
     * The role of the user
     */
    @Column
    private Role role = Role.USER;

    /**
     *The encrypted password of the user
     */
    @Column
    private String encryptedPassword;

    /**
     *
     * The groups associated with the user
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DBGroup> groups = Sets.newHashSet();

    @Column
    private boolean accountNonExpired = true;

    @Column
    private boolean accountNonLocked = true;

    @Column
    private boolean credentialsNonExpired =true;
    
    @Column
    private boolean enabled = true;


}
