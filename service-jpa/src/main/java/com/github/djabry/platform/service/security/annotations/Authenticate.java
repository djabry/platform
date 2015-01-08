package com.github.djabry.platform.service.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * Created by djabry on 05/01/15.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole('PERM_AUTHENTICATE')")
public @interface Authenticate {
}
