/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.wildfly.naming.java.permission;

import static org.junit.Assert.*;

import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;

import org.junit.Test;

/**
 * Big ol' JNDI permission test case.
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public class JndiPermissionTestCase {
    @Test public void testCollectionSerialization(){final PermissionCollection permissionCollection=new JndiPermission("","").newPermissionCollection();permissionCollection.add(new JndiPermission("foo/bar","createSubcontext,rebind"));permissionCollection.add(new JndiPermission("foo","addNamingListener"));permissionCollection.add(new JndiPermission("-","lookup,rebind"));final PermissionCollection other=(PermissionCollection)((SerializedJndiPermissionCollection)((JndiPermissionCollection)permissionCollection).writeReplace()).readResolve();Enumeration<Permission> e;assertNotNull(e=other.elements());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("foo/bar","createSubcontext,rebind"),e.nextElement());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("foo","addNamingListener"),e.nextElement());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("-","lookup,rebind"),e.nextElement());assertFalse(e.hasMoreElements());}
}
