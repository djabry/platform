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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.service.security;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.djabry.platform.domain.api.Permission;
import org.djabry.platform.domain.api.Role;
import org.djabry.platform.service.api.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author djabry
 */
@Service
public class DefaultPermissionMapper implements PermissionMapper {

   private Map<Role,Set<Permission>> m;
   
   @PostConstruct
   public void setupMap(){
       
       m = Maps.newLinkedHashMap();
       
       Set<Permission> userPermissions = Sets.newLinkedHashSet();
       userPermissions.add(Permission.READ_OWN);
       userPermissions.add(Permission.CREATE_OWN);
       userPermissions.add(Permission.UPDATE_OWN);
       userPermissions.add(Permission.DELETE_OWN);
       
       m.put(Role.USER, userPermissions);
       
       Set<Permission> adminPermissions = Sets.newLinkedHashSet(userPermissions);
       adminPermissions.add(Permission.READ_ANY);
       adminPermissions.add(Permission.CREATE_ANY);
       adminPermissions.add(Permission.DELETE_ANY);
       adminPermissions.add(Permission.UPDATE_ANY);

       m.put(Role.ADMINISTRATOR, adminPermissions);
       
   }

    @Override
    public Set<Permission> mapPermissions(Role role) {
        Set<Permission> permissions = Sets.newLinkedHashSet();
       Set<Permission> foundPermissions = m.get(role);
       
       if(foundPermissions!=null){
           permissions.addAll(foundPermissions);
       }
       
       return permissions;
    }

   
    
}
