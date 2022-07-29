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
package org.wildfly.clustering.web.infinispan.session;

import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;
import org.wildfly.clustering.web.session.SessionMetaData;

import static org.junit.Assert.*;

public class SimpleSessionMetaDataTestCase {
    private final SessionCreationMetaData creationMetaData = mock(SessionCreationMetaData.class);
    private final SessionAccessMetaData accessMetaData = mock(SessionAccessMetaData.class);

    private final SessionMetaData metaData = new SimpleSessionMetaData(this.creationMetaData, this.accessMetaData);

    @Test public void setLastAccessedTime(){Instant now=Instant.now();Duration sinceCreated=Duration.ofSeconds(10L);when(this.creationMetaData.getCreationTime()).thenReturn(now.minus(sinceCreated));this.metaData.setLastAccessedTime(now);verify(this.accessMetaData).setLastAccessedDuration(sinceCreated);}
}
