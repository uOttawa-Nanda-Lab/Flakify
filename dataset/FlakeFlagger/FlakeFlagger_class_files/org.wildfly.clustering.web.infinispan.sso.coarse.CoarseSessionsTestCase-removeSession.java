/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
package org.wildfly.clustering.web.infinispan.sso.coarse;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.wildfly.clustering.ee.Mutator;
import org.wildfly.clustering.web.sso.Sessions;

public class CoarseSessionsTestCase {
    private Mutator mutator = mock(Mutator.class);
    private Map<String, String> map = mock(Map.class);
    private Sessions<String, String> sessions = new CoarseSessions<>(this.map, this.mutator);

    @Test public void removeSession(){String deployment="deployment";when(this.map.remove(deployment)).thenReturn("id");this.sessions.removeSession(deployment);verify(this.mutator).mutate();reset(this.map,this.mutator);when(this.map.remove(deployment)).thenReturn(null);this.sessions.removeSession(deployment);verify(this.mutator,never()).mutate();}
}
