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
