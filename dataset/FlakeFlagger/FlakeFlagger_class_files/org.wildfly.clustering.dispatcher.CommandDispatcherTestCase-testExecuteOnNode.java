/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2018, Red Hat, Inc., and individual contributors
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

package org.wildfly.clustering.dispatcher;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.wildfly.clustering.group.Node;

/**
 * Validates behavior of default implementation of deprecated methods of {@link CommandDispatcher}.
 * @author Paul Ferraro
 */
@SuppressWarnings("deprecation")
public class CommandDispatcherTestCase {

    @Test public void testExecuteOnNode() throws CommandDispatcherException{CommandDispatcher<Void> dispatcher=mock(CommandDispatcher.class);try (CommandDispatcher<Void> subject=new TestCommandDispatcher<>(dispatcher)){Command<Object, Object> command=mock(Command.class);Node member=mock(Node.class);Object result=new Object();CompletableFuture<Object> future=new CompletableFuture<>();when(dispatcher.executeOnMember(same(command),same(member))).thenReturn(future);future.complete(result);CommandResponse<Object> response=subject.executeOnNode(command,member);try {assertSame(result,response.get());} catch (ExecutionException e){fail(e.getMessage());}Exception exception=new Exception();when(dispatcher.executeOnMember(same(command),same(member))).thenReturn(future);future.obtrudeException(exception);try {subject.executeOnNode(command,member).get();fail("Expected exception");} catch (ExecutionException e){assertSame(exception,e.getCause());}exception=new CancellationException();future.obtrudeException(exception);try {subject.executeOnNode(command,member).get();fail("Expected exception");} catch (CancellationException e){assertSame(exception,e);}catch (ExecutionException e){fail(e.getMessage());}} verify(dispatcher).close();}
}
