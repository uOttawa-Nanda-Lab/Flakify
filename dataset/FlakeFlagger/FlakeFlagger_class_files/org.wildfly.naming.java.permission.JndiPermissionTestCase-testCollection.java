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
    @Test public void testCollection(){final PermissionCollection permissionCollection=new JndiPermission("","").newPermissionCollection();Enumeration<Permission> e;permissionCollection.add(new JndiPermission("foo/bar","lookup,bind"));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));assertFalse(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));assertFalse(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));assertNotNull(e=permissionCollection.elements());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("foo/bar","lookup,bind"),e.nextElement());assertFalse(e.hasMoreElements());permissionCollection.add(new JndiPermission("foo/bar","unbind"));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));assertNotNull(e=permissionCollection.elements());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("foo/bar","lookup,bind,unbind"),e.nextElement());assertFalse(e.hasMoreElements());permissionCollection.add(new JndiPermission("-","lookup"));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","lookup")));assertTrue(permissionCollection.implies(new JndiPermission("","lookup")));assertFalse(permissionCollection.implies(new JndiPermission("baz/zap","lookup,bind,unbind")));assertFalse(permissionCollection.implies(new JndiPermission("baz/zap","unbind")));assertNotNull(e=permissionCollection.elements());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("foo/bar","lookup,bind,unbind"),e.nextElement());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("-","lookup"),e.nextElement());assertFalse(e.hasMoreElements());permissionCollection.add(new JndiPermission("-","bind,unbind"));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","lookup")));assertTrue(permissionCollection.implies(new JndiPermission("","lookup")));assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","lookup,bind,unbind")));assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","unbind")));assertNotNull(e=permissionCollection.elements());assertTrue(e.hasMoreElements());assertEquals(new JndiPermission("-","lookup,bind,unbind"),e.nextElement());assertFalse(e.hasMoreElements());}
}
