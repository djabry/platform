package org.djabry.platform.service;

import org.djabry.platform.service.config.TestConfig;
import org.djabry.platform.service.config.TestInitializer;
import org.djabry.platform.test.AuthenticationServiceIT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by djabry on 07/01/15.
 */
@ContextConfiguration(classes = {TestInitializer.class, TestConfig.class})
@ActiveProfiles(profiles = "dev")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringAuthenticationServiceIT extends AuthenticationServiceIT {

    @Override
    @Test
    public void testAll() {
        super.testAll();
    }
}
