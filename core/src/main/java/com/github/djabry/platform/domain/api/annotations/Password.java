package com.github.djabry.platform.domain.api.annotations;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Inherited;

/**
 * Created by djabry on 05/01/15.
 */

@NotNull
@Inherited
@Size(min = 6, max = 50)
public @interface Password {


}
