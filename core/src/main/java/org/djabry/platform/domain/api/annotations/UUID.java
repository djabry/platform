package org.djabry.platform.domain.api.annotations;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Inherited;

/**
 * Created by djabry on 05/01/15.
 */
@NotNull
@Inherited
@Size(min = 32, max = 32)
public @interface UUID {

}
