package com.github.djabry.platform.service.api;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by djabry on 07/01/15.
 */


public class DomainServices {

    private static final DomainServices instance = new DomainServices();
    private Set<DomainService> services = new LinkedHashSet<DomainService>();

    private DomainServices() {


    }

    public static DomainServices getInstance() {

        return instance;
    }

    public void registerDomainService(DomainService service) {
        this.services.add(service);

    }

    public <S extends DomainService> S findService(Class<S> serviceClass) {


        Iterator<DomainService> iterator = services.iterator();
        while (iterator.hasNext()) {
            DomainService service = iterator.next();
            if (serviceClass.isAssignableFrom(service.getClass())) {

                return (S) service;
            }

        }
        System.out.println("Failed to find service for " + serviceClass);
        return null;
    }

}
