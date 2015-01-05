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

package org.djabry.platform.domain.api;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by djabry on 03/01/15.
 *
 * This interface represents security objects only accessible to internal security processes
 *
 */
public interface UserAccount<U extends User,G extends Group> extends DomainObject<U>{

    /**
     * @return get the user associated with the security setup
     */
    @NotNull
    U getUser();
    void setUser(@NotNull U user);

    /**
     * @return The role associated with the user
     */
    @NotNull
    Role getRole();
    void setRole(@NotNull Role role);


    /**
     * @return The groups associated with the user
     */
    @NotNull
    Set<G> getGroups();
    void setGroups(@NotNull Set<G> groups);


    /**
     * @return the encrypted password of the user
     */
    @NotNull
    @NotBlank
    String getEncryptedPassword();
    void setEncryptedPassword(@NotNull @NotBlank String encryptedPassword);


    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired), <code>false</code> if no longer valid
     * (ie expired)
     */

    public boolean isAccountNonExpired(); 

    /**
     * @return Whether the user account is not locked
     */

    public boolean isAccountNonLocked();
    
    /**
     * Indicates whether the user's credentials (password) has expired. Expired credentials prevent
     * authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired), <code>false</code> if no longer
     * valid (ie expired)
     */

    public boolean isCredentialsNonExpired();



    public boolean isEnabled();

}
