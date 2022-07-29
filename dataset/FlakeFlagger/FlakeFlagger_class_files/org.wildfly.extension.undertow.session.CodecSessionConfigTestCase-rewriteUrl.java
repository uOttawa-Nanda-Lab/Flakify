/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017, Red Hat, Inc., and individual contributors
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

package org.wildfly.extension.undertow.session;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.SessionConfig;

import org.jboss.as.web.session.SessionIdentifierCodec;
import org.junit.Test;

/**
 * Unit test for {@link CodecSessionConfig}
 * @author Paul Ferraro
 */
public class CodecSessionConfigTestCase {

    private final SessionConfig config = mock(SessionConfig.class);
    private final SessionIdentifierCodec codec = mock(SessionIdentifierCodec.class);

    private final SessionConfig subject = new CodecSessionConfig(this.config, this.codec);

    @Test public void rewriteUrl(){String url="http://test";String encodedUrl="http://test/session";String encodedSessionId="session.route";String sessionId="session";when(this.codec.encode(sessionId)).thenReturn(encodedSessionId);when(this.config.rewriteUrl(url,encodedSessionId)).thenReturn(encodedUrl);String result=this.subject.rewriteUrl(url,sessionId);assertSame(encodedUrl,result);}
}
