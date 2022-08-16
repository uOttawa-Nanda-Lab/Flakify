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

    @Test public void testSubmitOnCluster() throws CommandDispatcherException{CommandDispatcher<Void> dispatcher=mock(CommandDispatcher.class);try (CommandDispatcher<Void> subject=new TestCommandDispatcher<>(dispatcher)){Command<Object, Object> command=mock(Command.class);Node completedMember=mock(Node.class);Node exceptionMember=mock(Node.class);Node cancelledMember=mock(Node.class);Object result=new Object();Exception exception=new Exception();CompletableFuture<Object> completedFuture=CompletableFuture.completedFuture(result);CompletableFuture<Object> exceptionFuture=new CompletableFuture<>();exceptionFuture.completeExceptionally(exception);CompletableFuture<Object> cancelledFuture=new CompletableFuture<>();cancelledFuture.cancel(false);Map<Node, CompletionStage<Object>> futures=new HashMap<>();futures.put(completedMember,completedFuture);futures.put(exceptionMember,exceptionFuture);futures.put(cancelledMember,cancelledFuture);when(dispatcher.executeOnGroup(same(command))).thenReturn(futures);Map<Node, Future<Object>> responses=subject.submitOnCluster(command);assertSame(completedFuture,responses.get(completedMember));assertSame(exceptionFuture,responses.get(exceptionMember));assertSame(cancelledFuture,responses.get(cancelledMember));} verify(dispatcher).close();}
}
