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
package org.wildfly.clustering.web.undertow.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;
import javax.servlet.http.HttpServletRequest;

import io.undertow.security.api.AuthenticatedSessionManager.AuthenticatedSession;
import io.undertow.security.idm.Account;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.SessionConfig;
import io.undertow.server.session.SessionListener;
import io.undertow.server.session.SessionListener.SessionDestroyedReason;
import io.undertow.server.session.SessionListeners;
import io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.wildfly.clustering.ee.Batch;
import org.wildfly.clustering.ee.BatchContext;
import org.wildfly.clustering.ee.Batcher;
import org.wildfly.clustering.web.session.Session;
import org.wildfly.clustering.web.session.SessionAttributes;
import org.wildfly.clustering.web.session.SessionManager;
import org.wildfly.clustering.web.session.SessionMetaData;

/**
 * Unit test for {@link DistributableSession}.
 *
 * @author Paul Ferraro
 */
public class DistributableSessionTestCase {
    private final UndertowSessionManager manager = mock(UndertowSessionManager.class);
    private final SessionConfig config = mock(SessionConfig.class);
    private final Session<LocalSessionContext> session = mock(Session.class);
    private final Batch batch = mock(Batch.class);
    private final Consumer<HttpServerExchange> closeTask = mock(Consumer.class);

    private final io.undertow.server.session.Session adapter = new DistributableSession(this.manager, this.session, this.config, this.batch, this.closeTask);

    @Test public void changeSessionId(){HttpServerExchange exchange=new HttpServerExchange(null);SessionConfig config=mock(SessionConfig.class);this.validate(exchange,session->session.changeSessionId(exchange,config));SessionManager<LocalSessionContext, Batch> manager=mock(SessionManager.class);Batcher<Batch> batcher=mock(Batcher.class);BatchContext context=mock(BatchContext.class);Session<LocalSessionContext> session=mock(Session.class);SessionAttributes oldAttributes=mock(SessionAttributes.class);SessionAttributes newAttributes=mock(SessionAttributes.class);SessionMetaData oldMetaData=mock(SessionMetaData.class);SessionMetaData newMetaData=mock(SessionMetaData.class);LocalSessionContext oldContext=mock(LocalSessionContext.class);LocalSessionContext newContext=mock(LocalSessionContext.class);SessionListener listener=mock(SessionListener.class);SessionListeners listeners=new SessionListeners();listeners.addSessionListener(listener);String oldSessionId="old";String newSessionId="new";String name="name";Object value=new Object();Instant now=Instant.now();Duration interval=Duration.ofSeconds(10L);AuthenticatedSession authenticatedSession=new AuthenticatedSession(null,null);when(this.manager.getSessionManager()).thenReturn(manager);when(manager.getBatcher()).thenReturn(batcher);when(batcher.resumeBatch(this.batch)).thenReturn(context);when(manager.createIdentifier()).thenReturn(newSessionId);when(manager.createSession(newSessionId)).thenReturn(session);when(this.session.getAttributes()).thenReturn(oldAttributes);when(this.session.getMetaData()).thenReturn(oldMetaData);when(session.getAttributes()).thenReturn(newAttributes);when(session.getMetaData()).thenReturn(newMetaData);when(oldAttributes.getAttributeNames()).thenReturn(Collections.singleton(name));when(oldAttributes.getAttribute(name)).thenReturn(value);when(newAttributes.setAttribute(name,value)).thenReturn(null);when(oldMetaData.getLastAccessedTime()).thenReturn(now);when(oldMetaData.getMaxInactiveInterval()).thenReturn(interval);when(this.session.getId()).thenReturn(oldSessionId);when(session.getId()).thenReturn(newSessionId);when(this.session.getLocalContext()).thenReturn(oldContext);when(session.getLocalContext()).thenReturn(newContext);when(oldContext.getAuthenticatedSession()).thenReturn(authenticatedSession);when(this.manager.getSessionListeners()).thenReturn(listeners);String result=this.adapter.changeSessionId(exchange,config);assertSame(newSessionId,result);verify(newMetaData).setLastAccessedTime(now);verify(newMetaData).setMaxInactiveInterval(interval);verify(config).setSessionId(exchange,newSessionId);verify(newContext).setAuthenticatedSession(same(authenticatedSession));verify(listener).sessionIdChanged(this.adapter,oldSessionId);verify(context).close();}


    private <R> void validate(Consumer<io.undertow.server.session.Session> consumer) {
        this.validate(null, consumer);
    }

    @SuppressWarnings("unchecked")
    private <R> void validate(HttpServerExchange exchange, Consumer<io.undertow.server.session.Session> consumer) {
        when(this.session.isValid()).thenReturn(false, true);

        try {
            consumer.accept(this.adapter);
            fail("Invalid session should throw IllegalStateException");
        } catch (IllegalStateException e) {
            verify(this.closeTask).accept(exchange);
            reset(this.closeTask);
        }
    }
}
