package com.github.djabry.platform.service.api;

import com.github.djabry.platform.domain.api.annotations.Password;
import com.github.djabry.platform.domain.api.annotations.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by djabry on 09/01/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class DefaultLoginRequest implements LoginRequest, Serializable {


    /**
     * The requested username
     */
    @Username
    String username;

    /**
     * The requested password
     */
    @Password
    String password;
}
