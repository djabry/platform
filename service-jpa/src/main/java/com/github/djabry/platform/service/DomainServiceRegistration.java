package com.github.djabry.platform.service;

import com.github.djabry.platform.service.api.DomainService;
import com.github.djabry.platform.service.api.DomainServices;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by djabry on 07/01/15.
 */

@Component
public class DomainServiceRegistration implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DomainServices dSP = DomainServices.getInstance();
        Map<String, DomainService> beans = applicationContext.getBeansOfType(DomainService.class);
        Iterator<Map.Entry<String, DomainService>> iterator = beans.entrySet().iterator();
        while (iterator.hasNext()) {
            dSP.registerDomainService(iterator.next().getValue());
        }
    }
}
