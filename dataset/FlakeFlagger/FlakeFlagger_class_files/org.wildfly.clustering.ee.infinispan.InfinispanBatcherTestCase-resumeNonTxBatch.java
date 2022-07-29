/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
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
package org.wildfly.clustering.ee.infinispan;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.junit.After;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.wildfly.clustering.ee.Batch;
import org.wildfly.clustering.ee.BatchContext;
import org.wildfly.clustering.ee.Batcher;

/**
 * Unit test for {@link InfinispanBatcher}.
 * @author Paul Ferraro
 */
public class InfinispanBatcherTestCase {
    private final TransactionManager tm = mock(TransactionManager.class);
    private final Batcher<TransactionBatch> batcher = new InfinispanBatcher(this.tm);

    @Test public void resumeNonTxBatch() throws Exception{TransactionBatch existingBatch=mock(TransactionBatch.class);InfinispanBatcher.setCurrentBatch(existingBatch);TransactionBatch batch=mock(TransactionBatch.class);try (BatchContext context=this.batcher.resumeBatch(batch)){verifyZeroInteractions(this.tm);assertSame(batch,InfinispanBatcher.getCurrentBatch());} verifyZeroInteractions(this.tm);assertSame(existingBatch,InfinispanBatcher.getCurrentBatch());}
}
